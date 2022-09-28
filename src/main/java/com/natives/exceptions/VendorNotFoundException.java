package com.natives.exceptions;

public class VendorNotFoundException extends RuntimeException {

    public VendorNotFoundException(String message) {
        super(message);
    }
}
