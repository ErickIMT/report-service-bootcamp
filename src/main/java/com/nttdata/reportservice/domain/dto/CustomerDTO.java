package com.nttdata.reportservice.domain.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class CustomerDTO {

    private String id;
    private String name;
    private String lastName;
    private String document;
    private String address;
    private String email;
    private String phone;
    private CustomerTypeDTO customerType;

}
