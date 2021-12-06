package com.masters.group.exercise1.models;

import com.masters.twelve.TotalPayable;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Cart {
    private static final NumberFormat fmt = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
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

    public void addToTotalAmount(double amount){ this.totalAmount = this.totalAmount + amount;}

    public String getCartDetails(){
        AtomicInteger ctr = new AtomicInteger();
        var totalPayable = orders
                .stream().collect(Collectors.teeing(
                        Collectors.counting(),
                        Collectors.summingDouble(o -> (o.getProduct().getPrice() * o.getQuantity())),
                        (count, sum)  -> new TotalPayable(Math.toIntExact(count), sum)
                ));

        return """
               Total Amount Due: %.2f
               Total Amount Compact: %s
               Number of Items: %d
               """.formatted(totalPayable.getTotal(), fmt.format(totalPayable.getTotal()), totalPayable.getCount());
    }
}
