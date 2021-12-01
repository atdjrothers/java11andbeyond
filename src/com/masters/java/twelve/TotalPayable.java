package com.masters.java.twelve;

public class TotalPayable {
    private double total;
    private int count;

    public TotalPayable(int count, double total) {
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
