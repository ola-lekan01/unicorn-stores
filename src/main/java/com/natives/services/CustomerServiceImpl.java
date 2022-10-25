package com.natives.services;

import com.natives.data.dtos.requests.CustomerRegistrationRequest;
import com.natives.data.dtos.requests.LoginRequest;
import com.natives.data.dtos.requests.ProductPurchaseRequest;
import com.natives.data.dtos.responses.CustomerRegistrationResponse;
import com.natives.data.dtos.responses.LoginResponse;
import com.natives.data.models.Customer;
import com.natives.data.models.Product;
import com.natives.data.repositories.*;
import com.natives.exceptions.OrderNotFoundException;
import com.natives.exceptions.ProductNotFoundException;
import com.natives.utils.validators.AccountValidation;
import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final AccountValidation accountValidation = new AccountValidation();
    private final ProductService productService = new ProductServiceImpl();

    @Override
    public CustomerRegistrationResponse register(CustomerRegistrationRequest customerRegistrationRequest) {
        accountValidation.isValidEmail(customerRegistrationRequest.getEmail());
        accountValidation.isValidPhoneNumber(customerRegistrationRequest.getPhoneNumber());
        accountValidation.isValidPassword(customerRegistrationRequest.getPassword());

        Customer savedCustomer = createBuyer(customerRegistrationRequest);
        return buildBuyerRegistrationResponse(savedCustomer);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Customer foundCustomer = customerRepository.findByEmail(loginRequest.getEmail());
        LoginResponse loginResponse = new LoginResponse();
        if(foundCustomer.getPassword().equals(loginRequest.getPassword())) loginResponse.setMessage("Login Successful");
        else loginResponse.setMessage("Authentication Error");
        return loginResponse;
    }

    private CustomerRegistrationResponse buildBuyerRegistrationResponse(Customer savedCustomer) {
        CustomerRegistrationResponse response = new CustomerRegistrationResponse();
        response.setMessage("User Registration Successful");
        response.setStatusCode(201);
        response.setUserId(savedCustomer.getId());
        return response;
    }

    private Customer createBuyer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = new Customer();

        customer.setEmail(customerRegistrationRequest.getEmail());
        customer.setPassword(customerRegistrationRequest.getPassword());
        customer.setPhoneNumber(customerRegistrationRequest.getPhoneNumber());
        Set<String> customerAddressList = customer.getDeliveryAddresses();
        customerAddressList.add(customerRegistrationRequest.getAddress());
        return customerRepository.save(customer);
    }

    @Override
    public String orderProduct(ProductPurchaseRequest productPurchaseRequest) {
        Customer customer = customerRepository.findById(productPurchaseRequest.getCustomerId());
        //search for product
        Product product = productService.getProductById(productPurchaseRequest.getProductId());
        if(product == null) throw new ProductNotFoundException("Product Not Found");
        //validate quantity exists
        if(product.getQuantity() >= productPurchaseRequest.getQuantity()){
            customer.getOrders().add(product);
            product.setQuantity(product.getQuantity() - productPurchaseRequest.getQuantity());
            customerRepository.save(customer);

            return "Order Successful";
        }
        else throw new OrderNotFoundException("Order Not Found Exception");
    }
}