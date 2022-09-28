package com.natives.data.models;

import lombok.Data;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Data
public class Buyer extends User{
    private String firstName;
    private String lastName;
    private Set<String> deliveryAddresses = new TreeSet<>();
}
