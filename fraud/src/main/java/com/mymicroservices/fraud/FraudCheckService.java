package com.mymicroservices.fraud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class FraudCheckService {
    @Autowired
    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public FraudCheckService(FraudCheckHistoryRepository fraudCheckHistoryRepository) {
        this.fraudCheckHistoryRepository = fraudCheckHistoryRepository;
    }

    public boolean isFraudulentCustomer(Integer customerID){
        fraudCheckHistoryRepository.save(FraudCheckHistory.builder()
                        .customerId(customerID)
                        .isFraudster(false)
                        .createAt(LocalDateTime.now())
                        .build());
        return false;
    }
}
