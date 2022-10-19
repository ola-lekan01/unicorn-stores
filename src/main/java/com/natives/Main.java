package com.natives;

import com.natives.data.dtos.requests.CustomerRegistrationRequest;
import com.natives.data.dtos.requests.LoginRequest;
import com.natives.services.CustomerService;
import com.natives.services.CustomerServiceImpl;
import com.natives.services.ProductService;
import com.natives.services.ProductServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;

@SpringBootApplication

public class Main {
    private static final Scanner reader = new Scanner(System.in);
    private static final CustomerService customerService = new CustomerServiceImpl();
    private static final ProductService productService = new ProductServiceImpl();
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    public static void setUp(){
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

    private static int getCustomerInput(){
        return reader.nextInt();
    }

    private static void processCustomerRequest(int intent){
        if (intent == 1) {
            var registeredCustomerRequest = customerRegistrationForm();
            var registeredCustomerResponse = customerService.register(registeredCustomerRequest);
            System.out.println(registeredCustomerResponse);
        }

        if(intent == 2){
           var loginRequest = customerLogin();
           var loginResponse = customerService.login(loginRequest);
            System.out.println(loginResponse);
        }
        if(intent ==3){

        }
        if (intent == 4) System.exit(4);

        setUp();
    }

    private static LoginRequest customerLogin() {
        LoginRequest loginRequest = new LoginRequest();

        var email = getEmailAddress();
        loginRequest.setEmail(email);

        var password = getPassword();
        loginRequest.setPassword(password);

        return loginRequest;
    }

    private static CustomerRegistrationRequest customerRegistrationForm() {
        CustomerRegistrationRequest customerRegistrationRequest = new CustomerRegistrationRequest();

        var email = getEmailAddress();
        customerRegistrationRequest.setEmail(email);

        System.out.println("Address: ");
        customerRegistrationRequest.setAddress(reader.next());

        System.out.println("Enter Phone Number: ");
        customerRegistrationRequest.setPhoneNumber(reader.next());

        var password = getPassword();
        customerRegistrationRequest.setPassword(password);

        return customerRegistrationRequest;
    }

    private static String getPassword() {
        System.out.println("Enter Password: ");
        return reader.next();
    }


    private static String getEmailAddress() {
        System.out.println("Enter Email: ");
        return reader.next();
    }


}