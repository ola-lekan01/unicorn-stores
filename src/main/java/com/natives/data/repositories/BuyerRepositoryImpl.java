package com.natives.data.repositories;

import com.natives.data.models.Buyer;
import com.natives.exceptions.BuyerNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class BuyerRepositoryImpl implements BuyerRepository{
    private final List<Buyer> buyers = new ArrayList<>();

    @Override
    public Buyer save(Buyer buyer) {
        buyer.setId(generateId());
        buyers.add(buyer);
        return buyer;
    }

    @Override
    public Buyer findById(int id) {
        for (var buyer : buyers) {
            if(buyer.getId() == id) return buyer;
        }
    throw new BuyerNotFoundException("Buyer Not found Exception");
    }

    @Override
    public List<Buyer> findAll() {
        return buyers;
    }

    @Override
    public void delete(Buyer buyer) {
            for (int i = 0; i < buyers.size(); i++) {
                if(buyer.equals(buyers.get(i))) buyers.remove(buyer);
                else throw new BuyerNotFoundException("Buyer Not Found Exception");
            }
    }

    @Override
    public void deleteAll() {
        buyers.clear();
    }

    private int generateId(){
        return buyers.size() + 1;
    }
}