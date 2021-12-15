package com.masters.group.exercise2.models;

public class Product {

    private final String name;
    private final double price;
    private final String type;
    private final String category;

    public Product(String name, double price, String type, String category){
        this.name = name;
        this.category = category;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public String toString(){
        return """
                {
                     name: %s,
                     price: %s,
                     type: %s,
                     category: %s
                }
                """.formatted(name, price, type, category);
    }
}
