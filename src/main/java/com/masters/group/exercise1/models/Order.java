package com.masters.group.exercise1.models;

public class Order {

    private int quantity;
    private Product product;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String displayProduct(Product product, int quantity){
        return "name: %s | price: %s x %d | %f".formatted(product.getName(), product.getPrice(), quantity, product.getPrice() * Double.valueOf(quantity));
    }
}
