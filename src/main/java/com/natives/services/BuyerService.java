package com.natives.services;

import com.natives.data.dtos.BuyerRegistrationRequest;
import com.natives.data.dtos.BuyerRegistrationResponse;
import com.natives.data.dtos.ProductPurchaseRequest;

public interface BuyerService {
    BuyerRegistrationResponse register(BuyerRegistrationRequest buyerRegistrationRequest);

    String orderProduct(ProductPurchaseRequest productPurchaseRequest);

}
