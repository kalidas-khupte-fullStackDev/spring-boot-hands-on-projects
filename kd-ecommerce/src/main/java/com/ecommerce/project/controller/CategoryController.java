package com.ecommerce.project.controller;

import com.ecommerce.project.api.response.model.CategoryResponse;
import com.ecommerce.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/public/")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("categories/view")
    public ResponseEntity<CategoryResponse> getCategories() {
        CategoryResponse fetchedCategoriesRes = categoryService.getCategories();
        return new ResponseEntity<>(fetchedCategoriesRes, HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }
}
