package com.natives.services;

import com.natives.data.dtos.requests.CustomerRegistrationRequest;
import com.natives.data.dtos.requests.LoginRequest;
import com.natives.data.dtos.responses.CustomerRegistrationResponse;
import com.natives.data.dtos.requests.ProductPurchaseRequest;
import com.natives.data.dtos.responses.LoginResponse;

public interface CustomerService {
    CustomerRegistrationResponse register(CustomerRegistrationRequest customerRegistrationRequest);
    LoginResponse login(LoginRequest loginRequest);
    String orderProduct(ProductPurchaseRequest productPurchaseRequest);

}
