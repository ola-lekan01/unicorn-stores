package com.natives.data.dtos.responses;

import lombok.Data;

@Data
public class CustomerRegistrationResponse {
    private int userId;
    private String message;
    private int statusCode;

    @Override
    public String toString() {
        return String.format("{\n\tUser-ID: %d\n\tMessage: %s\n\tStatus Code: %d\n}",userId, message,statusCode);
    }
}