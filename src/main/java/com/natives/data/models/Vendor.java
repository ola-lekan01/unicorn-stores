package com.natives.data.models;


import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Data

public class Vendor extends User{
    private String storeNumber;
    private Set<String> storeAddress = new HashSet<>();
}
