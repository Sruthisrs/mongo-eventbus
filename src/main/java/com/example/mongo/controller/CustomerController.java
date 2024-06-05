package com.example.mongo.controller;

import com.example.mongo.model.Customer;
import com.example.mongo.model.Orders;
import com.example.mongo.model.Products;
import com.example.mongo.service.CustomerService;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;


@RestController
public class CustomerController {


    private final CustomerService customerService;

    private static final Logger logger = Logger.getLogger(String.valueOf(CustomerController.class));


    public MongoTemplate mongoTemplate;

    public CustomerController(CustomerService customerService ) {
        this.customerService = customerService;
      //  this.orderService = orderService;
    }

    @PostMapping("/addCustomer")
    public void addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
    }

    @GetMapping("/getCustomer")
    public Customer getCustomer(@RequestParam String firstName){
        return customerService.getCustomer(firstName);
    }

    @PostMapping("/placeOrder")
    public void placeOrder(@RequestBody Orders order) {
        //collectionType = "order";
        //logger.info(order.toString());
        customerService.createOrderFor(order);
    }

    @GetMapping("/findAllOrders")
    public List<Orders> findAllOrders() {
        return customerService.findAllOrders();
    }

    @PostMapping("/updateFirstNameCustomer")
    public void updateFirstNameCustomer(@RequestParam String lastName) {
        customerService.updateFirstNameCustomer(lastName);
    }

    @PostMapping("/updateAddress")
    public void updateAddress(@RequestBody Customer customer) {
        customerService.updateAddress(customer);
    }

    @GetMapping("/getAllCustomerWithProdcuts")
    public List<Customer> getAllCustomerWithProdcuts() {
        return customerService.getAllCustomerWithProdcuts();
    }

    @GetMapping("getProducts")
    public List<Products> getProducts() {
        return customerService.getAllProducts();
    }

    @GetMapping("/getCustomersWithOrderStatus")
    public List<Document> getCustomersWithOrderStatus() {
        return customerService.getCustomersWithOrderStatus();
    }

    @GetMapping("/criterias")
    public List<Customer> getCustomersWithSomeCriteria(){
        return customerService.getCustomersWithCriterias();
    }



}
