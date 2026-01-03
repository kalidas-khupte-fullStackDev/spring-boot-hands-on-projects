package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/public/")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("categories/view")
    public ResponseEntity<Object> getCategories(){
        try {
            List<Category> fetchedCategories = categoryService.getCategories();
            return new ResponseEntity<>(fetchedCategories, HttpStatusCode.valueOf(HttpStatus.OK.value()));
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

}
