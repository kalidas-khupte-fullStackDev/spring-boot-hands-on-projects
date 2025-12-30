package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("api/public/categories")
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    @PostMapping("api/admin/createCategory")
    public String createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }
}
