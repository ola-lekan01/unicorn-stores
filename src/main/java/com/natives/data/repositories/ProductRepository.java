package com.natives.data.repositories;


import com.natives.data.models.Category;
import com.natives.data.models.Product;

import java.util.List;

public interface ProductRepository {
    Product save(Product product);
    Product findById(int id);
    Product findByCategory(Category category);
    List<Product> findAll();
    void delete(Product product);

    void deleteAll();
}
