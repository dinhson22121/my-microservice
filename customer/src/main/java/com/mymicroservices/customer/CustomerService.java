package com.mymicroservices.customer;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RestTemplate restTemplate;
    public void regisCustomer(CustomerRegistrationRequest request) {
        Customer customerBuilder = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .build();

        customerRepository.saveAndFlush(customerBuilder);
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                    FraudCheckResponse.class,
                    customerBuilder.getId()
        );
        if (fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("Fraudster");
        }

    }
}
