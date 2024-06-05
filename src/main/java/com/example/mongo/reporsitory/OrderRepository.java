package com.example.mongo.reporsitory;

import com.example.mongo.model.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Orders, String> {

    public Orders findByStatus(String status);
    public Orders findAllByCustomerName(String customerName);

}
