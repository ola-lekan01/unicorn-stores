package com.natives.services;

import com.natives.data.dtos.requests.AddProductRequest;
import com.natives.data.dtos.responses.AddProductResponse;
import com.natives.data.models.Category;
import com.natives.data.models.Product;
import com.natives.data.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
public class ProductServiceImpl implements ProductService{
    private static ProductRepository productRepository;
    @Override
    public AddProductResponse addProduct(AddProductRequest addproductRequest) {
        Product product = new Product();
        product.setPrice(addproductRequest.getPrice());
        product.setCategory(Category.valueOf(addproductRequest.getCategory().toUpperCase()));
        product.setName(addproductRequest.getName());
        var savedProduct = productRepository.save(product);

        AddProductResponse response = new AddProductResponse();
        response.setProductId(savedProduct.getId());
        response.setMessage("Product Added to Cart Successfully");
        response.setStatusCode(201);

        return response;
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
}