package com.natives.data.repositories;

import com.natives.data.models.Category;
import com.natives.data.models.Product;
import com.natives.exceptions.ProductNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    private final List<Product> products = new ArrayList<>();
    private final List<Product> foundProducts = new ArrayList<>();
    @Override
    public Product save(Product product) {
        product.setId(generateId());
        products.add(product);
        return product;
    }

    private int generateId() {
        return products.size() + 1;
    }

    @Override
    public Product findById(int id) {
        for (var product : products) {
            if(product.getId() == id) return product;
        }
        throw new ProductNotFoundException("Product Not found Exception");
    }

    @Override
    public List<Product> findByCategory(Category category) {
        for (var product : products) {
            if(product.getCategory() == category) foundProducts.add(product);
        }
        if(foundProducts.isEmpty()) throw new ProductNotFoundException("Product Not found Exception");
        return foundProducts;
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void delete(Product product) {
        var removedProducts = products.remove(product);
        if(!removedProducts)throw new ProductNotFoundException("Product Not found Exception");
    }

    @Override
    public void deleteAll() {
        products.clear();
    }
}
