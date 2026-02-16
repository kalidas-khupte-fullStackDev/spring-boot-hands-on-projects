package com.ecommerce.product.services;

import com.ecommerce.product.dtos.ProductRequest;
import com.ecommerce.product.dtos.ProductResponse;
import com.ecommerce.product.models.Product;
import com.ecommerce.product.repositories.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final static Logger logger = LoggerFactory.getLogger(ProductService.class);
    // We use EntityManager to force session clearing for the demo
    @PersistenceContext
    private EntityManager entityManager;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = new Product();
        updateProductFromRequest(product, productRequest);
        Product savedProduct = productRepository.save(product);
        return mapToProductResponse(savedProduct);
    }

    private ProductResponse mapToProductResponse(Product savedProduct) {
        ProductResponse response = new ProductResponse();
        response.setId(savedProduct.getId());
        response.setName(savedProduct.getName());
        response.setActive(savedProduct.getActive());
        response.setCategory(savedProduct.getCategory());
        response.setDescription(savedProduct.getDescription());
        response.setPrice(savedProduct.getPrice());
        response.setImageUrl(savedProduct.getImageUrl());
        response.setStockQuantity(savedProduct.getStockQuantity());
        return response;
    }

    private void updateProductFromRequest(Product product, ProductRequest productRequest) {
        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setImageUrl(productRequest.getImageUrl());
        product.setStockQuantity(productRequest.getStockQuantity());
    }

    public Optional<ProductResponse> updateProduct(Long id, ProductRequest productRequest) {
        // 1. First call: Hits the Database
        logger.info("Fetching product for the first time...");
        Product product1 = productRepository.findById(id).orElseThrow();

        // SQL: select * from user where id = ?

        // 2. Second call: Hits the L1 Cache (No DB call)
        logger.info("Fetching product for the second time...");

        Optional<Product> product2 = productRepository.findById(id);
        Product productCache = product2.orElseThrow();

        // Output confirmation
        logger.info("Obj comparison {}" ,product1 == productCache); // true (Same object reference)
        return product2.map(existingProduct -> {
                    updateProductFromRequest(existingProduct, productRequest);
                    Product savedProduct = productRepository.save(existingProduct);
                    return mapToProductResponse(savedProduct);
                });
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findByActiveTrue().stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }

    public boolean deleteProduct(Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setActive(false);
                    productRepository.save(product);
                    return true;
                }).orElse(false);
    }

    public List<ProductResponse> searchProducts(String keyword) {
        return productRepository.searchProducts(keyword).stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<ProductResponse> getProductById(String id) {
        // --- Call 1 ---
        Long productId = Long.valueOf(id);
        logger.info("--- 1. Fetching Product (Should hit DB) ---");
        Product p1 = productRepository.findByIdAndActiveTrue(productId).orElseThrow();
        // Hibernate Query: select * from product where id = ?
        // Result is put into L1 (Session) AND Redis (L2)

        // Clear L1 Cache (Session) to force a look at L2
        entityManager.clear();

        // --- Call 2 ---
        logger.info("--- 2. Fetching Product again (Should hit Redis) ---");
        Optional<Product> p2 = productRepository.findByIdAndActiveTrue(productId);

        Optional<ProductResponse> productResponse = p2.map(this::mapToProductResponse);
        // NO SQL Query will appear in your console here!
        p2.ifPresent(product -> logger.info("Loaded product: " + product.getName()));
        return productResponse;
    }
}
