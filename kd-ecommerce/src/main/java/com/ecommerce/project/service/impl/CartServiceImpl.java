package com.ecommerce.project.service.impl;

import com.ecommerce.project.dtos.CartDTO;
import com.ecommerce.project.dtos.ProductDTO;
import com.ecommerce.project.exceptions.custom.APIException;
import com.ecommerce.project.exceptions.custom.ResourceNotFoundException;
import com.ecommerce.project.model.Cart;
import com.ecommerce.project.model.CartItem;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.model.User;
import com.ecommerce.project.repository.CartItemsRepository;
import com.ecommerce.project.repository.CartRepository;
import com.ecommerce.project.repository.ProductRepository;
import com.ecommerce.project.service.CartService;
import com.ecommerce.project.utils.AuthUtils;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class CartServiceImpl implements CartService {

    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private AuthUtils authUtilsService;

    @Override
    @Transactional
    public CartDTO addItemToCart(Long productId, Integer quantity) {

        // Quantity Validation
        if (quantity == 0) {
            throw new APIException("Quantity can't be: " + quantity);
        }

        // Fetch product details
        Product productToAdd = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException(productId, "productId", "Product"));

        // Fetch existing card or create new one if not available
        Cart cart = createNewOrGetExistingCart();

        //Get or Create Cart Item For provided Product Id & Cart Id only not for all globally cart Items
        CartItem cartItem = cartItemsRepository.findCartItemByCartIdAndProductId(cart.getId(), productId);

        if (cartItem != null) {
            throw new APIException(productToAdd.getProductName() + " is already present in this cart");
        }

        // Validation regarding product stock
        if (productToAdd.getQuantity() == 0) {
            throw new APIException(productToAdd.getProductName() + " is out of stock");
        }

        if (productToAdd.getQuantity() < quantity) {
            throw new APIException("Quantity request for " + productToAdd.getProductName() + " can't be full filled due to stock concerns. Available quantity: " + productToAdd.getQuantity());
        }

        //Save Cart Item
        CartItem newCartItem = new CartItem();
        newCartItem.setCart(cart);
        newCartItem.setProduct(productToAdd);
        newCartItem.setProductPrice(productToAdd.getSpecialPrice());
        newCartItem.setDiscount(productToAdd.getDiscount());
        newCartItem.setQuantity(quantity);

        cartItemsRepository.save(newCartItem);

        // Updated the product stock in future code placeholder
        // productToAdd.setQuantity(productToAdd.getQuantity() - quantity);

        // Return updated cart
        cart.addProduct(newCartItem);
        cartRepository.save(cart);

        Stream<ProductDTO> productDTOStream = cart.getCartItems().stream().map(item -> {
            ProductDTO productDTO = modelMapper.map(item.getProduct(), ProductDTO.class);
            productDTO.setQuantity(item.getQuantity());
            return productDTO;
        });

        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
        // Cart DOA to DTO mapping
        cartDTO.setProducts(productDTOStream.toList());
        return cartDTO;
    }

    @Override
    public CartDTO getCurrentUserCart(String email, Long cartId) {
        Cart cart = cartRepository.findCartByEmailIdAndCartId(cartId, email).orElseThrow(() -> new ResourceNotFoundException(cartId, "cartId", "Carts"));
        List<ProductDTO> productDTOList = cart.getCartItems().stream().map(cartItem -> {
            ProductDTO productDTO = modelMapper.map(cartItem.getProduct(), ProductDTO.class);
            logger.info("Product DTO Before quantity set {}", productDTO);
            productDTO.setQuantity(cartItem.getQuantity());
            logger.info("Product DTO After quantity set {}", productDTO);
            return productDTO;
        }).toList();
        CartDTO userCart = modelMapper.map(cart, CartDTO.class);
        userCart.setProducts(productDTOList);
        return userCart;
    }

    @Override
    public List<CartDTO> getAllCarts() {
        List<Cart> allCarts = cartRepository.findAll().stream().toList();

        if (allCarts.isEmpty()) {
            throw new APIException("No carts present");
        }

        return allCarts.stream().map(cart -> {
            List<ProductDTO> productDTOList = cart.getCartItems().stream().map(cartItem -> {
                ProductDTO productDTO = modelMapper.map(cartItem.getProduct(), ProductDTO.class);
                logger.info("getAllCarts : Product DTO Before quantity set {}", productDTO);
                productDTO.setQuantity(cartItem.getQuantity());
                logger.info("getAllCarts : Product DTO After quantity set {}", productDTO);
                return productDTO;
            }).toList();
            CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
            cartDTO.setProducts(productDTOList);
            return cartDTO;
        }).toList();
    }

    public Cart createNewOrGetExistingCart() {
        // Get User details from AuthUtils
        User user = authUtilsService.getCurrentLoggedInUserDetails();
        // Fetch existing card or create new one if not available
        Cart cart = cartRepository.findCartByEmailId(user.getEmail()).orElse(new Cart());
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    @Transactional
    public CartDTO updateProductQuantity(Long productId, Integer operationValue) {
        User currentUser = authUtilsService.getCurrentLoggedInUserDetails();
        Cart cart = cartRepository.findCartByEmailId(currentUser.getEmail()).orElseThrow(() -> new ResourceNotFoundException(currentUser.getUsername(), "userName", "Carts"));
        // Fetch product details
        Product productToUpdate = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException(productId, "productId", "Product"));
        if (operationValue == 1 && productToUpdate.getQuantity() == 0) {
            throw new APIException("Product is out of stock");
        }
        if (productToUpdate.getQuantity() < operationValue) {
            throw new APIException("Please, make an order of the " + productToUpdate.getProductName()
                    + " less than or equal to the quantity " + productToUpdate.getQuantity() + ".");
        }
        List<CartItem> cartItems = cart.getCartItems();
        if (cartItems == null) {
            throw new APIException("Cart is empty with no cart items!!!");
        }
        cartItems = cartItems.stream().filter(cartItemElem -> Objects.equals(cartItemElem.getProduct().getProductId(), productId)).toList();
        if (cartItems.isEmpty()) {
            throw new APIException("Product " + productToUpdate.getProductName() + " not available in the cart!!!");
        }

        CartItem cartItemToUpdate = cartItems.getFirst();
        logger.info("Targeted Cart Item {}", cartItemToUpdate);
        // Calculate new quantity
        int newQuantity = cartItemToUpdate.getQuantity() + operationValue;
        // Validation to prevent negative quantities
        if (newQuantity < 0) {
            throw new APIException("The resulting quantity cannot be negative.");
        }

        if(newQuantity == 0){
            deleteProductFromCart(cart.getId(), productId);
        }else{
        cartItemToUpdate.setProductPrice(productToUpdate.getSpecialPrice());
        cartItemToUpdate.setQuantity(newQuantity);
        cartItemToUpdate.setDiscount(productToUpdate.getDiscount());
        cart.setTotalPrice(cart.getTotalPrice() + (cartItemToUpdate.getProductPrice() * operationValue));
        cartRepository.save(cart);
        }

        CartItem updatedItem = cartItemsRepository.save(cartItemToUpdate);
        if(updatedItem.getQuantity() == 0){
            cartItemsRepository.deleteById(updatedItem.getCartItemId());
        }

        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
        List<CartItem> cartItemsUpdated = cart.getCartItems();
        Stream<ProductDTO> productStream = cartItemsUpdated.stream().map(item -> {
            ProductDTO prd = modelMapper.map(item.getProduct(), ProductDTO.class);
            prd.setQuantity(item.getQuantity());
            return prd;
        });
        cartDTO.setProducts(productStream.toList());

        return cartDTO;
    }

    @Override
    @Transactional
    public String deleteProductFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException(cartId, "cartId", "Carts"));
        // Fetch product details
        CartItem cartItem = cartItemsRepository.findCartItemByCartIdAndProductId(cartId, productId);
        if(cartItem == null){
            throw new ResourceNotFoundException(productId, "ProductId", "Product");
        }
        Double newTotalPrice = (cart.getTotalPrice() - (cartItem.getProductPrice() * cartItem.getQuantity()));
        cart.setTotalPrice(newTotalPrice);
       cartItemsRepository.deleteCartItemByCartIdAndProductId(cartId, productId);

        return "Product " + cartItem.getProduct().getProductName() + " removed from the cart !!!";
    }
}
