package com.ecommerce.project.service;

import com.ecommerce.project.dtos.CartDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public interface CartService {

    CartDTO addItemToCart(Long productId, Integer quantity);

    CartDTO getCurrentUserCart(@NotEmpty @Size(max = 50) @Email String email, Long cartId);

    List<CartDTO> getAllCarts();

    CartDTO updateProductQuantity(Long productId, Integer operationValue);

    String deleteProductFromCart(Long cartId, Long productId);
}
