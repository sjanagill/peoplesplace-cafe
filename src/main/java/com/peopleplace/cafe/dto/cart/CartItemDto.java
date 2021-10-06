package com.peopleplace.cafe.dto.cart;

import com.peopleplace.cafe.model.Cart;
import com.peopleplace.cafe.model.Product;

public class CartItemDto {
    private Long id;
    private Long userId;
    private Integer quantity;
    private Product product;
    private Product subProduct;
    private Double totalCost;

    public CartItemDto() {
    }

    public CartItemDto(Cart cart) {
        this.setId(cart.getId());
        this.setUserId(cart.getUser().getId());
        this.setQuantity(cart.getQuantity());
        this.setProduct(cart.getProduct());
        this.setSubProduct(cart.getProduct().getSubProduct());
        this.setTotalCost(cart.getTotalCost());
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", quantity=" + quantity +
                ", productName=" + product.getName() +
                ", subProductName=" + product.getSubProduct().getName() +
                ", totalCost=" + totalCost +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Product getSubProduct() {
        return subProduct;
    }

    public void setSubProduct(Product subProduct) {
        this.subProduct = subProduct;
    }
}
