package com.mymicroservices.customer;

public record CustomerRegistrationRequest(String name, String lastName, String email, String phoneNumber) {
}
