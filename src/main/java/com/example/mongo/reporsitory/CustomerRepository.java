package com.example.mongo.reporsitory;

import java.util.List;

import com.example.mongo.model.Customer;
import com.example.mongo.model.Products;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {


    Customer findByFirstName(String firstName);
    List<Customer> findByLastName(String lastName);


    @Query(value = "{'products.productName': 1}")
    List<Customer> findAllCustomerProducts();

    @Query("{ 'lastname' : ?0 }")
    @Update("{ '$set' : { 'firstName' : ?1 } }")
    void updateFirstName(String lastName,String firstName);

    @Query("{ 'products': { '$exists': true } }")
    List<Customer> findAllCustomerWithProdcuts();


}
