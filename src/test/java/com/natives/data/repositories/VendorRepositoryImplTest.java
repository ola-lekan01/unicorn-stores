package com.natives.data.repositories;

import com.natives.data.models.Vendor;
import com.natives.exceptions.VendorNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VendorRepositoryImplTest {
    private Vendor firstVendor, secondVendor;
    private static final VendorRepository vendorRepository = new VendorRepositoryImpl();
    @BeforeEach
    void setUP(){
        firstVendor = new Vendor();
        Set<String> storeAddress = new HashSet<>();
        storeAddress.add("312, Herbert Macaulay, Yaba");
        firstVendor.setStoreAddress(storeAddress);
        firstVendor.setEmail("natives@store.com");
        firstVendor.setPhoneNumber("08067873474");

        secondVendor = new Vendor();
        Set<String> storeAddress2 = new HashSet<>();
        storeAddress.add("312, Palace Way, Yaba");
        firstVendor.setStoreAddress(storeAddress2);
        firstVendor.setEmail("natives@store.com");
        firstVendor.setPhoneNumber("08067873474");
    }

    @AfterEach
    void tearDown(){
        vendorRepository.deleteAll();
    }

    @Test
    void save() {
        assertEquals(0, firstVendor.getId());
        vendorRepository.save(firstVendor);
        assertEquals(1, firstVendor.getId());
        vendorRepository.save(secondVendor);
        assertEquals(2, secondVendor.getId());
    }

    @Test
    void findByIdReturnsAnRepositoryOrThrowsAnErrorForAnInvalidId() {
        vendorRepository.save(firstVendor);
        vendorRepository.save(secondVendor);

        var foundVendor = vendorRepository.findById(2);
        assertEquals(foundVendor, secondVendor);
        assertThrows(VendorNotFoundException.class, ()-> vendorRepository.findById(3));
    }

    @Test
    void findAll() {
        vendorRepository.save(firstVendor);
        vendorRepository.save(secondVendor);
        var numberOfVendorsInDB = vendorRepository.findAll();
        assertEquals(2, numberOfVendorsInDB.size());
    }

    @Test
    void delete() {
        var savedVendor = vendorRepository.save(firstVendor);
        assertEquals(1, vendorRepository.findAll().size());
        vendorRepository.delete(savedVendor);
        assertThrows(VendorNotFoundException.class, ()-> vendorRepository.findById(savedVendor.getId()));
    }

    @Test
    void testThatExceptionIsThrownWhenDeleteFails(){
        var firstV = vendorRepository.save(firstVendor);
        var secondV = vendorRepository.save(secondVendor);
        vendorRepository.delete(firstV);
        assertThrows(VendorNotFoundException.class, ()-> vendorRepository.delete(firstV));
    }
}