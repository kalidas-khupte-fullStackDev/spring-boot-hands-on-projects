package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> getCategories();

    public String createCategory(Category category);

    public String updateCategory(Long categoryId, Category category);

    public String deleteCategory(Long categoryId);
}
