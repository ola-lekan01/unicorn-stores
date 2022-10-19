package com.natives.utils.validators;

import com.natives.exceptions.BuyerRegistrationException;

public class AccountValidation {

    public void isValidPassword(String password) {
        if (!password.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}"))
            throw new BuyerRegistrationException(String.format("Password is Weak! %n"));
    }

    public void isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 11)
            throw new BuyerRegistrationException(String.format("%s Invalid Phone Number", phoneNumber));
    }

    public void isValidEmail(String email) {
        if (!email.contains("@"))
            throw new BuyerRegistrationException(String.format("%s Invalid Email.", email));
    }
}