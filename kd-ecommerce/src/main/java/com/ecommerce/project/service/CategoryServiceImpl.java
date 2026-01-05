package com.ecommerce.project.service;

import com.ecommerce.project.exceptions.custom.APIException;
import com.ecommerce.project.exceptions.custom.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()){
            throw new APIException("No categories present till now");
        }
        return categories;
    }

    @Override
    public String createCategory(Category category) {
        Category foundCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(foundCategory != null){
            throw new APIException(String.format("%s Category already present in System", category.getCategoryName()));
        }else{
        categoryRepository.save(category);
        return category.getCategoryName() + " Category Added Successfully";
        }
    }

    @Override
    public String updateCategory(Long categoryId, Category category) {
        Optional<Category> optional_CatToBeUpdate = categoryRepository.findById(categoryId);
        Category categoryToBeUpdate =  optional_CatToBeUpdate.orElseThrow(() -> new ResourceNotFoundException(categoryId , "categoryId", "Category"));
        categoryToBeUpdate.setCategoryName(category.getCategoryName());
        categoryRepository.save(categoryToBeUpdate);
        return "Category with an ID "+ categoryToBeUpdate.getCategoryId() + " has been Updated Successfully!.";
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Optional<Category> op_CatToDelete = categoryRepository.findById(categoryId);
        Category categoryToDelete = op_CatToDelete.orElseThrow(() -> new ResourceNotFoundException(categoryId , "categoryId", "Category"));
        categoryRepository.delete(categoryToDelete);
        return categoryToDelete.getCategoryName().trim() + " Category Deleted Successfully!.";
    }
}
