package com.masters.group.exercise2.models;

public class Order {

    private double quantity;
    private Product product;

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String displayProduct(Product product, double quantity){
        return "%s | price: %s %s x %.2f | %.2f".formatted(product.getName(), product.getPrice(), product.getType(), quantity, product.getPrice() * quantity);
    }
}
