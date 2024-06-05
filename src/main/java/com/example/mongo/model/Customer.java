package com.example.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "customers")
public class Customer {


    private String id;

    private String firstName;
    private String lastName;
    private List<Products> products;
    private Address address;

}
