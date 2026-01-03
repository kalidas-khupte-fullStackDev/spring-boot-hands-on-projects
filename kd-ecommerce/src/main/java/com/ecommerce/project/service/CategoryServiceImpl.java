package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public String createCategory(Category category) {
        categoryRepository.save(category);
        return category.getCategoryName() + " Category Added Successfully";
    }

    @Override
    public String updateCategory(Long categoryId, Category category) {
        Optional<Category> optional_CatToBeUpdate = categoryRepository.findById(categoryId);
        Category categoryToBeUpdate =  optional_CatToBeUpdate.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Oops Expected Category ID not found in DB"));
        categoryToBeUpdate.setCategoryName(category.getCategoryName());
        categoryRepository.save(categoryToBeUpdate);
        return "Category with an ID "+ categoryToBeUpdate.getCategoryId() + " has been Updated Successfully!.";
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Optional<Category> op_CatToDelete = categoryRepository.findById(categoryId);
        Category categoryToDelete = op_CatToDelete.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Oops Expected Category Resource not found in DB for Deletion"));
        categoryRepository.delete(categoryToDelete);
        return categoryToDelete.getCategoryName().trim() + " Category Deleted Successfully!.";
    }
}
