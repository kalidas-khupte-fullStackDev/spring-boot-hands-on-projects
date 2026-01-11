package com.ecommerce.project.service;

import com.ecommerce.project.dtos.ProductDTO;

public interface ProductService {
    ProductDTO addProduct(ProductDTO productDTO, Long categoryId);
}
