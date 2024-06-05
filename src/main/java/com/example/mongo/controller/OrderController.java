/*
package com.example.mongo.controller;

import com.example.mongo.model.Order;
import com.example.mongo.service.CustomerService;
import com.example.mongo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

//@RestController
public class OrderController {

    private static final Logger logger = Logger.getLogger(String.valueOf(OrderController.class));

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        //collectionType = "order";
        logger.info(order.toString());
        return orderService.createOrderFor(order);
    }

    @GetMapping("/findAllOrders")
    public List<Order> findAllOrders() {
        return orderService.findAllOrders();
    }
}
*/
