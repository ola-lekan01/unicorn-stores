package com.natives.data.dtos.requests;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class AddProductRequest {
    private String name;
    private BigDecimal price;
    private String category;
}
