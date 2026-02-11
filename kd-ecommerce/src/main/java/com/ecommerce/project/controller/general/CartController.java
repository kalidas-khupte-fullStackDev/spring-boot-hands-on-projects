package com.ecommerce.project.controller.general;

import com.ecommerce.project.dtos.CartDTO;
import com.ecommerce.project.exceptions.custom.ResourceNotFoundException;
import com.ecommerce.project.model.Cart;
import com.ecommerce.project.model.User;
import com.ecommerce.project.repository.CartRepository;
import com.ecommerce.project.service.CartService;
import com.ecommerce.project.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cart/")
public class CartController {

    @Autowired
    private AuthUtils authUtils;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @PostMapping("add/products/{productId}/quantity/{quantity}")
    public ResponseEntity<CartDTO> addItemToCart(@PathVariable Long productId, @PathVariable Integer quantity){
        CartDTO updatedCart = cartService.addItemToCart(productId, quantity);
        return new ResponseEntity<CartDTO>(updatedCart, HttpStatusCode.valueOf(HttpStatus.CREATED.value()));

    }

    @GetMapping("all")
    public ResponseEntity<List<CartDTO>> getAllCarts(){
        List<CartDTO> allCarts = cartService.getAllCarts();
        return new ResponseEntity<List<CartDTO>>(allCarts, HttpStatusCode.valueOf(HttpStatus.FOUND.value()));

    }

    @GetMapping("get/user")
    public ResponseEntity<CartDTO> getUserCart(){
        User user = authUtils.getCurrentLoggedInUserDetails();
        Cart cart = cartRepository.findCartByEmailId(user.getEmail()).orElseThrow(() -> new ResourceNotFoundException("userEmailID", user.getEmail() , "Cart"));
        CartDTO currentUserCart = cartService.getCurrentUserCart(user.getEmail(), cart.getId());
        return new ResponseEntity<CartDTO>(currentUserCart, HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    @PutMapping("update/products/{productId}/{operation}")
    public ResponseEntity<CartDTO> updateProductQuantity(@PathVariable Long productId, @PathVariable String operation){
        Integer operationValue = operation.equalsIgnoreCase("decrease") ? -1 : 1;
        CartDTO updatedCartDTO = cartService.updateProductQuantity(productId ,operationValue);
        return new ResponseEntity<CartDTO>(updatedCartDTO, HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    @DeleteMapping("delete/{cartId}/products/{productId}")
    public ResponseEntity<?> deleteProductFromCart(@PathVariable Long cartId, @PathVariable Long productId){
       String status = cartService.deleteProductFromCart(cartId, productId);
        return new ResponseEntity<>(status, HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }


}
