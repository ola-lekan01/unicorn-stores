package com.natives.services;

import com.natives.data.dtos.requests.AddProductRequest;
import com.natives.data.dtos.responses.*;
import com.natives.data.models.Product;

public interface ProductService {
    AddProductResponse addProduct(AddProductRequest addproductRequest);
    Product getProductById(int id);

    Product save(Product product);
}
