package com.masters.group.exercise1.models;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<Order> orders = new ArrayList<>();

    private double totalAmount = 0;

    public double getTotalAmount() {
        return totalAmount;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order){
        orders.add(order);
    }

    public void addToTotalAmount(double amount){ this.totalAmount = this.totalAmount + amount;}

    public String displayCartDetails(){
        NumberFormat fmt = NumberFormat.getCompactNumberInstance();
        return """
                     Current Cart Contents:
                     Total Amount: %s
                     Total Amount Compact: %s
                     Number of Items: %s
                """.formatted(this.getTotalAmount(), fmt.format(this.getTotalAmount()), this.getOrders().size());
    }
}
