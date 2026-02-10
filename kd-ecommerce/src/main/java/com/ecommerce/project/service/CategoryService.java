package com.ecommerce.project.service;

import com.ecommerce.project.api.response.model.CategoryResponse;
import com.ecommerce.project.dtos.CategoryDTO;

public interface CategoryService {

    CategoryResponse getCategories(Integer pageNumber, Integer pageSize, String  sortBy, String sortOrder);

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDto);

    CategoryDTO deleteCategory(Long categoryId);

//    private static CategoryResponse getCategoriesPrvt() {
//        return null;
//    }
//
//    static void logWarning(String msg) {
//        CategoryResponse formatted = CategoryService.getCategoriesPrvt(); // Calling private method
//        System.out.println("WARN: " + formatted);
//    }


}
