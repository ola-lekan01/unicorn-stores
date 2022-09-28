package com.natives.data.repositories;

import com.natives.data.models.Buyer;
import com.natives.data.models.Category;
import com.natives.data.models.Product;
import com.natives.exceptions.BuyerNotFoundException;
import com.natives.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.natives.data.models.Category.COMPUTING;
import static com.natives.data.models.Category.ELECTRONICS;
import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryImplTest {
    private Product product;
    private Product product2;
    private ProductRepository productRepository = new ProductRepositoryImpl();

    @BeforeEach
    void setUp(){
        product = new Product();
        product2 = new Product();

        product.setName("Television");
        product.setCategory(ELECTRONICS);
        product.setPrice(BigDecimal.valueOf(5000));

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
        var foundProduct = productRepository.findByCategory(ELECTRONICS);
        assertEquals(savedProduct, foundProduct);
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
        var savedProduct2 = productRepository.save(product);
        assertEquals(2,  productRepository.findAll().size());
        productRepository.delete(savedProduct);
        assertEquals(1, productRepository.findAll().size());
        var finalSavedProduct = savedProduct;
        assertThrows(ProductNotFoundException.class, ()-> productRepository.delete(finalSavedProduct));

    }
}