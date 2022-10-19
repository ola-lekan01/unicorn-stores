package com.natives.data.repositories;

import com.natives.data.models.Customer;
import com.natives.exceptions.BuyerNotFoundException;
import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CustomerRepositoryImplTest {

    private static final CustomerRepository CUSTOMER_REPOSITORY = new CustomerRepositoryImpl();
    private Customer customer;
    private Customer customer2;

    @Test
    @BeforeEach
    void setUp(){
        customer = new Customer();
        customer.setFirstName("Lakes");
        customer.setLastName("Lavish");
        customer.setEmail("lakes@name.com");
        customer.setPassword("12345");
        customer.setPhoneNumber("08023505758");

        customer2 = new Customer();
        customer2.setFirstName("Herbs");
        customer2.setLastName("Gavin");
        customer2.setEmail("gavin@name.com");
        customer2.setPassword("34567");
        customer2.setPhoneNumber("08023505758");
    }

    @AfterEach
    void tearDown(){
        CUSTOMER_REPOSITORY.deleteAll();
    }

    @Test
    void saveTest() {
        assertEquals(0, customer.getId());
        var savedBuyer = CUSTOMER_REPOSITORY.save(customer);
        assertEquals(1, savedBuyer.getId());

        savedBuyer = CUSTOMER_REPOSITORY.save(customer2);
        assertEquals(2, savedBuyer.getId());

    }

    @Test
    void findByIdTestReturnsEntryAndThrowsExceptionWhenIdIsInValid() {
        var savedBuyer = CUSTOMER_REPOSITORY.save(customer);
        var savedBuyer2 = CUSTOMER_REPOSITORY.save(customer2);
        var foundBuyer = CUSTOMER_REPOSITORY.findById(1);
        assertEquals(foundBuyer, savedBuyer);
        assertThrows(BuyerNotFoundException.class, ()-> CUSTOMER_REPOSITORY.findById(3));
    }

    @Test
    void findAllTest() {
        var savedBuyer = CUSTOMER_REPOSITORY.save(customer);
        savedBuyer = CUSTOMER_REPOSITORY.save(customer2);
        List<Customer> listOfAllCustomers = CUSTOMER_REPOSITORY.findAll();
        assertEquals(2, listOfAllCustomers.size());
    }

    @Test
    void deleteTest() {
        var savedBuyer = CUSTOMER_REPOSITORY.save(customer);
        assertEquals(1,  CUSTOMER_REPOSITORY.findAll().size());
        CUSTOMER_REPOSITORY.delete(savedBuyer);
        assertThrows(BuyerNotFoundException.class, ()-> CUSTOMER_REPOSITORY.delete(savedBuyer));
    }

    @Test
    void deleteAllTest(){
        CUSTOMER_REPOSITORY.save(customer);
        CUSTOMER_REPOSITORY.save(customer2);
        assertEquals(2,  CUSTOMER_REPOSITORY.findAll().size());
        CUSTOMER_REPOSITORY.deleteAll();
        assertEquals(0, CUSTOMER_REPOSITORY.findAll().size());
    }
}