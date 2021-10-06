package com.peopleplace.cafe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "OrderItem")
public class OrderItem {

    @Id
    private Long id;

    private int quantity;

    private double price;

    private Date createdDate;

    private Order order;

    private Product product;

    @JsonIgnore
    private Product subProduct;

    public OrderItem() {
    }

    public OrderItem(Order order, Product product, int quantity, double price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.order = order;
        this.createdDate = new Date();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Product getSubProduct() {
        return subProduct;
    }

    public void setSubProduct(Product subProduct) {
        this.subProduct = subProduct;
    }

}