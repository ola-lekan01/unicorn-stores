package com.natives.data.dtos.requests;

import lombok.Data;
@Data
public class CustomerRegistrationRequest {
    private String email;
    private String address;
    private String phoneNumber;
    private String password;
}
