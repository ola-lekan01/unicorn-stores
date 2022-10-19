package com.natives.data.repositories;

import com.natives.data.models.Vendor;
import com.natives.exceptions.VendorNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class VendorRepositoryImpl implements VendorRepository {
    private final List<Vendor> vendors = new ArrayList<>();

    @Override
    public Vendor save(Vendor vendor) {
        vendor.setId(generateId());
        vendors.add(vendor);
        return vendor;
    }

    private int generateId() {
        return vendors.size() + 1;
    }

    @Override
    public Vendor findById(int id) {
        for (var vendor : vendors) {
            if(vendor.getId() == id) return vendor;
        }
        throw new VendorNotFoundException("Vendor Not Found !!!");
    }
    @Override
    public List<Vendor> findAll() {
        return vendors;
    }
    @Override
    public void delete(Vendor vendor) {
       var removedVendor = vendors.remove(vendor);
       if (!removedVendor) throw new VendorNotFoundException("Vendor Not Found");
    }

    @Override
    public void deleteAll() {
        vendors.clear();
    }
}
