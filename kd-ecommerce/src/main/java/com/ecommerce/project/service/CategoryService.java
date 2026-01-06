package com.ecommerce.project.service;

import com.ecommerce.project.api.response.model.CategoryResponse;
import com.ecommerce.project.dtos.CategoryDTO;

public interface CategoryService {

    CategoryResponse getCategories();

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDto);

    CategoryDTO deleteCategory(Long categoryId);
}
