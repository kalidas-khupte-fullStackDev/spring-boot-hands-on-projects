package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private Long categoryId = 0L;
    List<Category> categories = new ArrayList<>();


    @Override
    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public String createCategory(Category category) {
        category.setCategoryId(++categoryId);
        categories.add(category);
        return "Category Added Successfully";
    }
}
