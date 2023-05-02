package com.mymicroservices.customer;

public record CustomerRegistrationRequest(String firstName, String lastName, String email, String phoneNumber) {
}
