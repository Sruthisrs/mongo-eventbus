package com.example.mongo.model;


import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "order")
public class Orders {

    public String id;
    public String customerName;
    public String items;
    public String status;

    public Orders(String id, String customerName, String items, String status) {
        this.id = id;
        this.customerName = customerName;
        this.items = items;
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format(
                "Orders[id=%s, items='%s', status='%s', customerName='%s']",
                id, items, status, customerName);
    }

}
