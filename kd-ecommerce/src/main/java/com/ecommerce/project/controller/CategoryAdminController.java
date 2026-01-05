package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/admin/")
public class CategoryAdminController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("categories/add")
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category) {
        String status = categoryService.createCategory(category);
        return new ResponseEntity<>(status, HttpStatusCode.valueOf(HttpStatus.CREATED.value()));
    }

    @DeleteMapping("categories/delete/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        String status = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<String>(status, HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    @PutMapping("categories/update/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable long categoryId, @RequestBody Category category) {
        String status = categoryService.updateCategory(categoryId, category);
        return new ResponseEntity<>(status, HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

}
