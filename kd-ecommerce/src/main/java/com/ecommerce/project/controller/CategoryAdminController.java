package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
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
    public ResponseEntity<String> createCategory(@RequestBody Category category){
        try {
            String status = categoryService.createCategory(category);
            return new ResponseEntity<>(status, HttpStatusCode.valueOf(HttpStatus.CREATED.value()));
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    @DeleteMapping("categories/delete/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
        try {
            String status = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<String>(status, HttpStatusCode.valueOf(HttpStatus.OK.value()));
        } catch (ResponseStatusException e) {
            return new ResponseEntity<String>(e.getReason(), e.getStatusCode());
        }
    }

    @PutMapping("categories/update/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable long categoryId, @RequestBody Category category){
        try {
            String status = categoryService.updateCategory(categoryId, category);
            return new ResponseEntity<>(status, HttpStatusCode.valueOf(HttpStatus.OK.value()));
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

}
