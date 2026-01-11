package com.ecommerce.project.api.response.model;

import com.ecommerce.project.model.Category;


public class ProductResponse {
    private Long productId;
    private String name;
    private String description;
    private double price;
    private double specialPrice;
    private Integer discount;
    private Integer quantity;
    private Category category;
}
