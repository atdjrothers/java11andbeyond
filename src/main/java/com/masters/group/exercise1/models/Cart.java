package com.masters.group.exercise1.models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<Order> orders = new ArrayList<>();

    private double totalAmount;

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order){
        orders.add(order);
    }
}
