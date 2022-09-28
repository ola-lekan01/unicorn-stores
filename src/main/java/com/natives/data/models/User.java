package com.natives.data.models;

import lombok.Data;

@Data
public class User {
    private int id;
    private String email;
    private String password;
    private String phoneNumber;
}