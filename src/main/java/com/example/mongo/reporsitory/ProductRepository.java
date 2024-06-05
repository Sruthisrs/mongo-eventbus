package com.example.mongo.reporsitory;

import com.example.mongo.model.Customer;
import com.example.mongo.model.Products;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Products,String> {
    @Query(value = "{'products.productName': 1}")
    List<Customer> findAllCustomerProducts();
}
