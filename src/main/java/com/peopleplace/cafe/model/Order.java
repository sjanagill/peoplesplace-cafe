package com.peopleplace.cafe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peopleplace.cafe.dto.order.PlaceOrderDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "Order")
public class Order {

    @Id
    private Long id;

    private Date createdDate;

    private Double totalPrice;

    private String sessionId;

    private List<OrderItem> orderItems;

    @JsonIgnore
    private User user;

    public Order() {
    }

    public Order(PlaceOrderDto orderDto, User user, String sessionId) {
        this.user = user;
        this.createdDate = new Date();
        this.totalPrice = orderDto.getTotalPrice();
        this.sessionId = sessionId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}