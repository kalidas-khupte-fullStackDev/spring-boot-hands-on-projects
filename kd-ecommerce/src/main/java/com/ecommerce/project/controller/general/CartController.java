package com.ecommerce.project.controller.general;

import com.ecommerce.project.dtos.CartDTO;
import com.ecommerce.project.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/public/carts/")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("add/products/{productId}/quantity/{quantity}")
    public ResponseEntity<CartDTO> addItemToCart(@PathVariable Long productId, @PathVariable Integer quantity){
        CartDTO updatedCart = cartService.addItemToCart(productId, quantity);
        return new ResponseEntity<CartDTO>(updatedCart, HttpStatusCode.valueOf(HttpStatus.CREATED.value()));

    }
}
