package com.example.mongo.service;

import com.example.mongo.model.Customer;
import com.example.mongo.model.Orders;
import com.example.mongo.model.Products;
import com.example.mongo.reporsitory.CustomerRepository;
import com.example.mongo.reporsitory.OrderRepository;
import com.example.mongo.reporsitory.ProductRepository;
import com.mongodb.client.*;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private static final Logger logger = Logger.getLogger(String.valueOf(CustomerService.class));

    @Autowired
    private MongoTemplate mongoTemplate;


    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/");
    MongoDatabase database = mongoClient.getDatabase("demo");

    public CustomerService(CustomerRepository customerRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public void addCustomer(Customer customer) {
//        Customer customer1=customerRepository.save(customer);
//        return ResponseEntity.ok(customer1);
//        MongoCollection<Document> collection1 = database.getCollection("customers");
//        Document document1 = new Document();
//        //document1.append("id",customer.setId(id);).append("firstName",customer.firstName).append("lastName",customer.lastName);
//        document1.append("id",customer.getId()).
//                append("firstName",customer.getFirstName()).
//                append("lastName",customer.getLastName()).append("products",customer.getProducts()).
//                append("address",customer.getAddress());
//        collection1.insertOne(document1);
//        logger.info(collection1.toString());
        //return mongoTemplate.save(customer, "customers");
        customerRepository.save(customer);
    }

    public Customer getCustomer(String firstName) {
        return customerRepository.findByFirstName(firstName);
    }

    public void createOrderFor(Orders order) {
        MongoCollection<Document> collection1= database.getCollection("order");
        Document document1 = new Document();
        document1.append("id",order.id)
                .append("status",order.status).append("items",order.items)
                .append("customerName",order.customerName);
        collection1.insertOne(document1);
        logger.info(collection1.toString());
    }

    public List<Orders> findAllOrders() {
        return orderRepository.findAll();
    }

    public void updateFirstNameCustomer(@RequestParam String lastName) {
        //MongoCollection<Document> collection1= database.getCollection("customers");
        //List<Customer> customer1  = customerRepository.findByLastName(customer.lastName);
        //logger.info(customer1.toString());
        //customerRepository.updateFirstName(lastName, "guess");
        //return customer1;

        List<Customer> customers = customerRepository.findByLastName(lastName);
        customers.forEach(customer -> customer.setFirstName("updatedFirstName"));
        customerRepository.saveAll(customers);
    }

    public void updateAddress(Customer customer) {
        customerRepository.save(customer);

    }

    public List<Customer> getAllCustomerWithProdcuts() {
        return customerRepository.findAllCustomerWithProdcuts();
        //return customerRepository.findAllCustomerWithProdcuts();
    }

    public List<Customer> getAllCustomerWithProdcuts2() {
        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").is("pavan").
                and("products.productName").is("toybash"));
        return mongoTemplate.find(query,Customer.class);
    }

    public List<Products> getAllProducts() {
        List<Customer> customers =customerRepository.findAll();
        List<Products> productsList = new ArrayList<Products>();
        customers.forEach(customer -> {
            List<Products> products = customer.getProducts();
            productsList.addAll(products);
        });
        return productsList;
    }

    public List<Document> getCustomersWithOrderStatus() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("status").is("placed")),
                Aggregation.lookup("customers", "customerName", "firstName", "customerDetails"),
                Aggregation.unwind("customerDetails")
        );

        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation,"order", Document.class);//mongoTemplate.aggregate(aggregation, "order", Document.class);
        return results.getMappedResults();
    }

    public List<Customer> getCustomersWithCriterias() {
        Query query = new Query();
        query.addCriteria(Criteria.where("status").is("placed"));
        List<Orders> orders = mongoTemplate.find(query,Orders.class);
        //query.addCriteria(Criteria.where("firstName").regex("^S"));

        Set<String> customerNames = orders.stream().map(orders1 -> orders1.customerName).collect(Collectors.toSet());
        return getCustomerByNames(customerNames);

    }

    public List<Customer> getCustomerByNames(Set<String> customerNames){
        Query query2 = new Query();
        List<Customer> customerList = new ArrayList<>();
        for (String cust : customerNames){
            customerList.add(customerRepository.findByFirstName(cust));
        }
        return customerList;
    }

}
