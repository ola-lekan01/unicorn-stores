package com.natives.controller;

import com.natives.data.dtos.requests.CustomerRegistrationRequest;
import com.natives.data.dtos.requests.LoginRequest;
import com.natives.data.dtos.responses.CustomerRegistrationResponse;
import com.natives.data.dtos.responses.LoginResponse;
import com.natives.services.CustomerService;
import com.natives.services.CustomerServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")

public class CustomerController {
    private final CustomerService customerService = new CustomerServiceImpl();

    @PostMapping("/register")
    public CustomerRegistrationResponse register(@RequestBody CustomerRegistrationRequest customerRegistrationRequest){
        return customerService.register(customerRegistrationRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return customerService.login(loginRequest);
    }
}