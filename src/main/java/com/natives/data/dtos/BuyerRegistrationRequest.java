package com.natives.data.dtos;

import lombok.Data;

@Data
public class BuyerRegistrationRequest {
    private String email;
    private String address;
    private String phoneNumber;
    private String password;
}
