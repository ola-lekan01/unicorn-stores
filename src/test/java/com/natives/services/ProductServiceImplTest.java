package com.natives.services;

import com.natives.data.dtos.requests.AddProductRequest;
import com.natives.data.dtos.responses.AddProductResponse;
import org.junit.jupiter.api.*;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceImplTest {
    private final ProductService productService = new ProductServiceImpl();
    AddProductRequest addProductRequest;

    @BeforeEach
    void setUp(){
        addProductRequest = new AddProductRequest();
        addProductRequest.setName("Milo");
        addProductRequest.setPrice(BigDecimal.valueOf(500));
        addProductRequest.setCategory("Beverages");
    }
    @Test
    public void testAddProduct(){
        AddProductResponse response = productService.addProduct(addProductRequest);
        assertNotNull(response.getMessage());
        assertEquals(1, response.getProductId());
    }
//    @Test
//    void getProductById(){
//        AddProductResponse response = productService.addProduct(addProductRequest);
//        response.getProductId();
//        var foundProduct =
//    }
}