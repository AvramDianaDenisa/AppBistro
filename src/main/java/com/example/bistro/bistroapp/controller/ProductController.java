package com.example.bistro.bistroapp.controller;

import com.example.bistro.bistroapp.dto.ProductDTO;
import com.example.bistro.bistroapp.entity.Product;
import com.example.bistro.bistroapp.service.ProductService;
import com.example.bistro.bistroapp.entity.ProductMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ProductController.BASE_URL)
public class ProductController {
    public static final String BASE_URL = "/api/v1/products";
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
        Product product = productMapper.productDTOtoProduct(productDTO);
        Product addedProduct = productService.addProduct(product);
        ProductDTO addedProductDTO = productMapper.productToProductDTO(addedProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedProductDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return products.stream()
                .map(productMapper::productToProductDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        ProductDTO productDTO = productMapper.productToProductDTO(product);
        return ResponseEntity.ok(productDTO);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductDTO> updateProductPrice(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        Product productToUpdate = productMapper.productDTOtoProduct(productDTO);
        Product updatedProduct = productService.updateProductPrice(id, productToUpdate.getPrice());
        ProductDTO updatedProductDTO = productMapper.productToProductDTO(updatedProduct);
        return ResponseEntity.ok(updatedProductDTO);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> removeProduct(@PathVariable Long id) {
        productService.removeProduct(id);
        return ResponseEntity.noContent().build();
    }
}

