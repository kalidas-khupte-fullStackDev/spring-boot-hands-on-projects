package com.ecommerce.project.controller.admin;

import com.ecommerce.project.dtos.ProductDTO;
import com.ecommerce.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//http://localhost:8080/api/admin/products/category/1/add
@RestController
@RequestMapping("api/admin/products/")
public class ProductAdminController {

    @Autowired
    private ProductService productService;

    @PostMapping("category/{categoryId}/add")
    public ResponseEntity<ProductDTO> addProduct(@PathVariable Long categoryId, @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.addProduct(productDTO, categoryId), HttpStatusCode.valueOf(HttpStatus.CREATED.value()));
    }
}
