package com.natives.services;

import com.natives.data.dtos.BuyerRegistrationRequest;
import com.natives.data.models.Buyer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuyerServiceImplTest {

    private final BuyerService buyerService = new BuyerServiceImpl();
    private BuyerRegistrationRequest firstBuyerRegisterRequest;
    private BuyerRegistrationRequest secondBuyerRegisterRequest;



    @BeforeEach
    void setUp() {
        firstBuyerRegisterRequest = new BuyerRegistrationRequest();
        firstBuyerRegisterRequest.setEmail("lakes@gmail.com");
        firstBuyerRegisterRequest.setAddress("312 Herbert Macaulay Way, Yaba");
        firstBuyerRegisterRequest.setPhoneNumber("0806 453 8735");
        firstBuyerRegisterRequest.setPassword("Lakes12345!@#");

        secondBuyerRegisterRequest = new BuyerRegistrationRequest();
        secondBuyerRegisterRequest.setEmail("james@gmail.com");
        secondBuyerRegisterRequest.setAddress("312 Prince Way, Ikoyi");
        secondBuyerRegisterRequest.setPhoneNumber("0702 463 8776");
        secondBuyerRegisterRequest.setPassword("123James!@#");
    }

    @Test
    void register() {

    }

    @Test
    void orderProduct() {
    }
}