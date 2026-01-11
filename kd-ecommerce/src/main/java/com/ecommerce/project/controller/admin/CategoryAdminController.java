package com.ecommerce.project.controller.admin;

import com.ecommerce.project.dtos.CategoryDTO;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin/categories/")
public class CategoryAdminController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("add")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO category) {
        CategoryDTO categoryDTOAddResponse = categoryService.createCategory(category);
        return new ResponseEntity<>(categoryDTOAddResponse, HttpStatusCode.valueOf(HttpStatus.CREATED.value()));
    }

    @DeleteMapping("delete/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId) {
        CategoryDTO categoryDeleteDTORes = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(categoryDeleteDTORes, HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    @PutMapping("update/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable long categoryId, @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO categoryDTOUpdatedResponse = categoryService.updateCategory(categoryId, categoryDTO);
        return new ResponseEntity<>(categoryDTOUpdatedResponse, HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

}
