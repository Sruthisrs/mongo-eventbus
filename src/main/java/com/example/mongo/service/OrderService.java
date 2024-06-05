/*
package com.example.mongo.service;

import com.example.mongo.model.Customer;
import com.example.mongo.model.Order;
import com.example.mongo.reporsitory.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public ResponseEntity<Order> createOrderFor(Order order) {
        Order order1=orderRepository.save(order);
        return ResponseEntity.ok(order1);
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }
}
*/
