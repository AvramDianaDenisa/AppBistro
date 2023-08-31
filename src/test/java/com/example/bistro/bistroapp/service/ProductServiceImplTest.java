package com.example.bistro.bistroapp.service;

import com.example.bistro.bistroapp.entity.Product;
import com.example.bistro.bistroapp.repository.IngredientRepository;
import com.example.bistro.bistroapp.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Test getting product with ingredients")
    public void testGetProductWithIngredients() {
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Product retrievedProduct = productService.getProductWithIngredients(productId);

        assertNotNull(retrievedProduct);
        assertEquals(productId, retrievedProduct.getId());
    }

    @Test
    @DisplayName("Test getting al products")
    public void testGetAllProducts() {
        Product product1 = new Product();
        Product product2 = new Product();

        when(productRepository.findAll()).thenReturn(List.of(product1, product2));

        List<Product> products = productService.getAllProducts();

        assertEquals(2, products.size());
    }

    @Test
    @DisplayName("Test getting product by ID")
    public void testGetProductById() {
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Product retrievedProduct = productService.getProductById(productId);

        assertNotNull(retrievedProduct);
        assertEquals(productId, retrievedProduct.getId());
    }

    @Test
    @DisplayName("Test updating product price")
    public void testUpdateProductPrice() {
        Long productId = 1L;
        double newPrice = 15.0;
        Product product = new Product();
        product.setId(productId);
        product.setPrice(10.0);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Product updatedProduct = productService.updateProductPrice(productId, newPrice);

        assertNotNull(updatedProduct);
        assertEquals(newPrice, updatedProduct.getPrice());
    }

    @Test
    @DisplayName("Test removing product")
    public void testRemoveProduct() {
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        productService.removeProduct(productId);

        verify(productRepository, times(1)).delete(product);
    }

    @Test
    @DisplayName("Test getting top most wanted products")
    public void testGetTopMostWantedProducts() {
        int count = 3;
        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();

        when(productRepository.findTopMostWantedProducts(any())).thenReturn(List.of(product1, product2, product3));

        List<Product> topProducts = productService.getTopMostWantedProducts(count);

        assertEquals(count, topProducts.size());
    }
}

