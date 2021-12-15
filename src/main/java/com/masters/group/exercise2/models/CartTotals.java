package com.masters.group.exercise2.models;

public class CartTotals {
    private double total;
    private int count;

    public CartTotals(int count, double total) {
        this.total = total;
        this.count = count;
    }

    public double getTotal() {
        return total;
    }

    public int getCount() {
        return count;
    }
}
