package com.natives.data.dtos.requests;

import lombok.Data;
@Data
public class ProductPurchaseRequest {
    private int CustomerId;
    private int productId;
    private int quantity;
}