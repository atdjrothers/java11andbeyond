package com.masters.group.exercise2.utils;

import com.masters.group.exercise1.models.Product;

public class ProductHelper {

    public static Product getProduct(String input){
        String[] values = input.split(",");
        int ctr = -1;
        return new Product(values[++ctr], Double.parseDouble(values[++ctr]), values[++ctr], values[++ctr]);
    }
}
