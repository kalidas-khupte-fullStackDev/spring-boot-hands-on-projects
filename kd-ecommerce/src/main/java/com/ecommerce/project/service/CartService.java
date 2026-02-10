package com.ecommerce.project.service;

import com.ecommerce.project.dtos.CartDTO;

public interface CartService {

    CartDTO addItemToCart(Long productId, Integer quantity);
}
