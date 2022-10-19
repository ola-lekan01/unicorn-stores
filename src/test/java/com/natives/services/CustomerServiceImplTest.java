package com.natives.services;

import com.natives.data.dtos.requests.AddProductRequest;
import com.natives.data.dtos.requests.CustomerRegistrationRequest;
import com.natives.data.dtos.responses.CustomerRegistrationResponse;
import com.natives.data.dtos.requests.ProductPurchaseRequest;
import com.natives.exceptions.BuyerRegistrationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImplTest {

    private final CustomerService customerService = new CustomerServiceImpl();
    private final ProductService productService = new ProductServiceImpl();
    private CustomerRegistrationRequest firstBuyerRegisterRequest;
    private CustomerRegistrationRequest secondBuyerRegisterRequest;
    private final ProductPurchaseRequest productPurchaseRequest = new ProductPurchaseRequest();
    private AddProductRequest addProductRequest;


    @BeforeEach
    void setUp() {
        firstBuyerRegisterRequest = new CustomerRegistrationRequest();
        firstBuyerRegisterRequest.setEmail("lakes@gmail.com");
        firstBuyerRegisterRequest.setAddress("312 Herbert Macaulay Way, Yaba");
        firstBuyerRegisterRequest.setPhoneNumber("08064538735");
        firstBuyerRegisterRequest.setPassword("Lakes12345!@#");

        secondBuyerRegisterRequest = new CustomerRegistrationRequest();
        secondBuyerRegisterRequest.setEmail("james@gmail.com");
        secondBuyerRegisterRequest.setAddress("312 Prince Way, Ikoyi");
        secondBuyerRegisterRequest.setPhoneNumber("07024238776");
        secondBuyerRegisterRequest.setPassword("123James");

        addProductRequest = new AddProductRequest();
        addProductRequest.setCategory("beverages");
        addProductRequest.setName("Milo");
        addProductRequest.setPrice(BigDecimal.valueOf(150));


        ProductPurchaseRequest productPurchaseRequest = new ProductPurchaseRequest();
        productPurchaseRequest.setProductId(1);
        productPurchaseRequest.setQuantity(5);
    }

    @Test
    void register() {
        CustomerRegistrationResponse response = customerService.register(firstBuyerRegisterRequest);
        assertNotNull(response);
        assertEquals(201, response.getStatusCode());
    }
    @Test
    public void userWithInvalidUserThrowsAnException(){
        assertThrows(BuyerRegistrationException.class, ()-> customerService.register(secondBuyerRegisterRequest));
    }

    @Test
    void orderProduct() {
        var addProductResponse = productService.addProduct(addProductRequest);
        assertNotNull(addProductResponse);
        assertEquals(201, addProductResponse.getStatusCode());
        var response = customerService.orderProduct(productPurchaseRequest);
        assertNull(response);
    }
}