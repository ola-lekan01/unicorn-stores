package com.natives.data.repositories;

import com.natives.data.models.Product;
import com.natives.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.natives.data.models.Category.COMPUTING;
import static com.natives.data.models.Category.ELECTRONICS;
import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryImplTest {
    private Product product;
    private Product product2;
    private Product product3;
    private final ProductRepository productRepository = new ProductRepositoryImpl();

    @BeforeEach
    void setUp(){
        product = new Product();
        product2 = new Product();
        product3 = new Product();

        product.setName("Television");
        product.setCategory(ELECTRONICS);
        product.setPrice(BigDecimal.valueOf(5000));

        product3.setName("Radio");
        product3.setCategory(ELECTRONICS);
        product3.setPrice(BigDecimal.valueOf(1500));

        product2.setName("Laptop");
        product2.setCategory(COMPUTING);
        product2.setPrice(BigDecimal.valueOf(2000));
    }

    @AfterEach
    void tearDown(){
        productRepository.deleteAll();
    }

    @Test
    void save() {
        assertEquals(0, product.getId());
        var savedProduct = productRepository.save(product);
        assertEquals(1, savedProduct.getId());
        savedProduct = productRepository.save(product);
        assertEquals(2, savedProduct.getId());
    }

    @Test
    void findById() {
        var savedProduct = productRepository.save(product);
        var foundProduct = productRepository.findById(1);
        assertEquals(foundProduct, savedProduct);
    }

    @Test
    void findByCategory() {
        var savedProduct = productRepository.save(product);
        var savedProduct1 = productRepository.save(product3);
        var foundProduct = productRepository.findByCategory(ELECTRONICS);
        assertEquals(savedProduct.getCategory(), foundProduct.get(0).getCategory());
        assertEquals(savedProduct1.getCategory(), foundProduct.get(1).getCategory());
    }

    @Test
    void findAll() {
        var savedBuyer = productRepository.save(product);
        savedBuyer = productRepository.save(product2);
        var listOfAllProduct = productRepository.findAll();
        assertEquals(2, listOfAllProduct.size());
    }

    @Test
    void delete() {
        var savedProduct = productRepository.save(product2);
        assertEquals(1,  productRepository.findAll().size());
        productRepository.delete(savedProduct);
        assertThrows(ProductNotFoundException.class, () -> productRepository.findById(savedProduct.getId()));
    }

    @Test
    void testThatNonExistingProductThrowsException(){
        var savedProduct = productRepository.save(product2);
        productRepository.delete(savedProduct);
        assertThrows(ProductNotFoundException.class, ()-> productRepository.delete(savedProduct));
    }
}