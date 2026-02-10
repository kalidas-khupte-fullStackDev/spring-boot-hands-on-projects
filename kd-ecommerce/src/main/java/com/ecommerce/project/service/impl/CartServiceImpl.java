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
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        CartItem cartItem = cartItemsRepository.findCartItemByCartIdAndProductId(cart.getCartId(), productId);

        if(cartItem != null){
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

        newCartItem = cartItemsRepository.save(newCartItem);

        // Updated the product stock in future code placeholder

        // Return updated cart
        cart.setTotalPrice(cart.getTotalPrice() + (newCartItem.getProductPrice() * quantity));
      //  logger.info(" cart.getCartItems() : {}", cart.getCartItems());

        if(cart.getCartItems() != null){
            cart.getCartItems().add(newCartItem);
        }
        cartRepository.save(cart);

        //
        Stream<ProductDTO> productDTOStream = cart.getCartItems().stream().map(item -> {
            ProductDTO productDTO = modelMapper.map(item.getProduct(), ProductDTO.class);
            productDTO.setQuantity(item.getQuantity());
            return productDTO;
        });

        // Cart DOA to DTO mapping
        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
        cartDTO.setProducts(productDTOStream.toList());
        return cartDTO;
    }

    public Cart createNewOrGetExistingCart() {
        // Get User details from AuthUtils
        User user = authUtilsService.getCurrentLoggedInUserDetails();
        // Fetch existing card or create new one if not available
        Cart cart = cartRepository.findCartByEmailId(user.getEmail()).orElse(new Cart());
        cart.setUser(user);
        return cartRepository.save(cart);
    }
}
