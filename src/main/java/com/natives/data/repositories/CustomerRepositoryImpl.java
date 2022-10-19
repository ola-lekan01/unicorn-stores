package com.natives.data.repositories;

import com.natives.data.models.Customer;
import com.natives.exceptions.BuyerNotFoundException;
import com.natives.exceptions.CustomerNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    private final List<Customer> customers = new ArrayList<>();

    @Override
    public Customer save(Customer customer) {
        customer.setId(generateId());
        customers.add(customer);
        return customer;
    }

    @Override
    public Customer findById(int id) {
        for (var buyer : customers) {
            if(buyer.getId() == id) return buyer;
        }
    throw new BuyerNotFoundException("Customer Not found Exception");
    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public void delete(Customer customer) {
        var removedBuyer = customers.remove(customer);
        if(!removedBuyer) throw new BuyerNotFoundException("Customer Not Found Exception");
    }

    @Override
    public void deleteAll() {
        customers.clear();
    }

    @Override
    public Customer findByEmail(String email) {
        for (Customer customer : customers) {
            if (customer.getEmail().equalsIgnoreCase(email)) return customer;
        }
        throw new CustomerNotFoundException("Customer not Found");
    }

    private int generateId(){
        return customers.size() + 1;
    }
}