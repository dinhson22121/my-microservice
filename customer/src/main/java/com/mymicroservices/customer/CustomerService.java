package com.mymicroservices.customer;

import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository) {
    public void regisCustomer(CustomerRegistrationRequest request) {
        Customer customerBuilder = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .build();

        customerRepository.save(customerBuilder);
    }
}
