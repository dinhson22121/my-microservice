package com.mymicroservices.customer;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Customer implements Serializable {
    private Integer id;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;

}
