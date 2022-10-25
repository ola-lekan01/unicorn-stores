package com.natives;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.natives.data.dtos.requests.CustomerRegistrationRequest;
import com.natives.data.dtos.requests.LoginRequest;
import com.natives.services.CustomerService;
import com.natives.services.CustomerServiceImpl;
import com.natives.services.ProductService;
import com.natives.services.ProductServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

@SpringBootApplication

public class Main {
//    private static final Scanner reader = new Scanner(System.in);
    private static final CustomerService customerService = new CustomerServiceImpl();
    private static final ProductService productService = new ProductServiceImpl();
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        setUp();
    }

    public static void setUp() throws IOException {
        prompt();
        int intent = getCustomerInput();
        processCustomerRequest(intent);
    }

    public static void prompt(){
        System.out.println("""
                Welcome to Unicorn Stores, What would you like to do today?
                
                1.  Register
                2.  Login
                3.  Order Product
                4.  Exit
                """);
    }

    private static int getCustomerInput() throws IOException {
        return reader.read();
    }

    private static void processCustomerRequest(int intent) throws IOException {
        System.out.println("""
                Registration Page
                """);
        if (intent == 1) {
            var registeredCustomerRequest = customerRegistrationForm();

            var registeredCustomerResponse = customerService.register(registeredCustomerRequest);

            try {
                var jsonResponse = mapper.writeValueAsString(registeredCustomerResponse);
                System.out.println(jsonResponse);

            } catch (JsonProcessingException exception) {
                throw new RuntimeException(exception);
            }
        }

        if(intent == 2){
            System.out.println("""
                Login Page
                """);
           var loginRequest = customerLogin();
           var loginResponse = customerService.login(loginRequest);

            try {
                mapper.writeValueAsString(loginResponse);
                System.out.println(loginResponse);

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        if (intent ==3){

        }
        if (intent == 4) System.exit(4);

        setUp();
    }

    private static LoginRequest customerLogin() throws IOException {
        LoginRequest loginRequest = new LoginRequest();

        var email = getEmailAddress();
        loginRequest.setEmail(email);

        var password = getPassword();
        loginRequest.setPassword(password);

        return loginRequest;
    }

    private static CustomerRegistrationRequest customerRegistrationForm() throws IOException {
        CustomerRegistrationRequest customerRegistrationRequest = new CustomerRegistrationRequest();

        var email = getEmailAddress();
        customerRegistrationRequest.setEmail(email);

        System.out.println("Enter Address: ");
        customerRegistrationRequest.setAddress(reader.readLine());

        System.out.println("Enter Phone Number: ");
        customerRegistrationRequest.setPhoneNumber(reader.readLine());

        var password = getPassword();
        customerRegistrationRequest.setPassword(password);

        return customerRegistrationRequest;
    }

    private static String getPassword() throws IOException {
        System.out.println("Enter Password: ");
        return reader.readLine();
    }


    private static String getEmailAddress() throws IOException {
        System.out.println("Enter Email: ");
        return reader.readLine();
    }

}