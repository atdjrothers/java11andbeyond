package com.masters.group.exercise2.models;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.masters.group.exercise1.utils.Constants.TYPE_COUNTED_AS_ONE;

public class Cart {
    private static final NumberFormat fmt = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);

    static {
        fmt.setMinimumFractionDigits(2);
    }

    private final List<Order> orders = new ArrayList<>();

    private double totalAmount;

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order){
        orders.add(order);
    }

    public void addToTotalAmount(double amount){ this.totalAmount = this.totalAmount + amount;}

    public String getCartDetails(){

        var totalPayable = orders
                .stream().collect(Collectors.teeing(
                        Collectors.summingInt(((Order o) -> TYPE_COUNTED_AS_ONE.contains(o.getProduct().getType()) ? 1 : (int) o.getQuantity())),
                        Collectors.summingDouble(o -> (o.getProduct().getPrice() * o.getQuantity())),
                        CartTotals::new
                ));

        return """
               Total Amount Due: %.2f
               Total Amount Compact: %s
               Number of Items: %d
               """.formatted(totalPayable.getTotal(), fmt.format(totalPayable.getTotal()), totalPayable.getCount());
    }
}
