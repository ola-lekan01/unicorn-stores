package com.natives.data.dtos.responses;

import lombok.Data;

@Data
public class AddProductResponse {
    private String message;
    private int statusCode;
    private int productId;
}
