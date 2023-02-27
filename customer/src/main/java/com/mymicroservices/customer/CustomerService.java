package com.mymicroservices.customer;

import org.springframework.stereotype.Service;

@Service
public record CustomerService() {
    public void regisCustomer(CustomerRegistrationRequest request) {
        Customer customerBuilder = Customer.builder()
                .name(request.name())
                .lastName(request.lastName())
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .build();
    }
}
