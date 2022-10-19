package com.natives.data.repositories;

import com.natives.data.models.Customer;

import java.util.List;

public interface CustomerRepository {
    Customer save(Customer customer);
    Customer findById(int id);
    List<Customer> findAll();
    void delete(Customer customer);
    void deleteAll();
    Customer findByEmail(String email);
}
