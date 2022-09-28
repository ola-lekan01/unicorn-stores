package com.natives.data.repositories;

import com.natives.data.models.Buyer;
import com.natives.exceptions.BuyerNotFoundException;
import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class BuyerRepositoryImplTest {

    private static final BuyerRepository buyerRepository = new BuyerRepositoryImpl();
    private Buyer buyer;
    private Buyer buyer2;

    @Test
    @BeforeEach
    void setUp(){
        buyer = new Buyer();
        buyer.setFirstName("Lakes");
        buyer.setLastName("Lavish");
        buyer.setEmail("lakes@name.com");
        buyer.setPassword("12345");
        buyer.setPhoneNumber("08023505758");

        buyer2 = new Buyer();
        buyer2.setFirstName("Herbs");
        buyer2.setLastName("Gavin");
        buyer2.setEmail("gavin@name.com");
        buyer2.setPassword("34567");
        buyer2.setPhoneNumber("08023505758");
    }

    @AfterEach
    void tearDown(){
        buyerRepository.deleteAll();
    }

    @Test
    void saveTest() {
        assertEquals(0, buyer.getId());
        var savedBuyer = buyerRepository.save(buyer);
        assertEquals(1, savedBuyer.getId());

        savedBuyer = buyerRepository.save(buyer2);
        assertEquals(2, savedBuyer.getId());

    }

    @Test
    void findByIdTestReturnsEntryAndThrowsExceptionWhenIdIsInValid() {
        var savedBuyer = buyerRepository.save(buyer);
        var savedBuyer2 = buyerRepository.save(buyer2);
        var foundBuyer = buyerRepository.findById(1);
        assertEquals(foundBuyer, savedBuyer);
        assertThrows(BuyerNotFoundException.class, ()-> buyerRepository.findById(3));
    }

    @Test
    void findAllTest() {
        var savedBuyer = buyerRepository.save(buyer);
        savedBuyer = buyerRepository.save(buyer2);
        List<Buyer> listOfAllBuyers = buyerRepository.findAll();
        assertEquals(2, listOfAllBuyers.size());
    }

    @Test
    void deleteTest() {
        var savedBuyer = buyerRepository.save(buyer);
        var savedBuyer2 = buyerRepository.save(buyer2);
        assertEquals(2,  buyerRepository.findAll().size());
        buyerRepository.delete(savedBuyer);
        assertEquals(1, buyerRepository.findAll().size());
        assertThrows(BuyerNotFoundException.class, ()-> buyerRepository.delete(savedBuyer));
    }

    @Test
    void deleteAllTest(){
        buyerRepository.save(buyer);
        buyerRepository.save(buyer2);
        assertEquals(2,  buyerRepository.findAll().size());
        buyerRepository.deleteAll();
        assertEquals(0, buyerRepository.findAll().size());
    }
}