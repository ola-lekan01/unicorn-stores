package com.natives.exceptions;

public class BuyerNotFoundException extends RuntimeException{
    public BuyerNotFoundException(String message) {
        super(message);
    }
}
