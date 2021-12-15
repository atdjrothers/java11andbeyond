package com.masters.session5;

import java.util.ArrayList;
import java.util.List;

public class Exercise4 {

    public static void main(String[] args) {
        final List<PaymentMethod> paymentMethodList = new ArrayList<>();
        paymentMethodList.add(new BankAccount());
        paymentMethodList.add(new CheckingAccount());
        paymentMethodList.add(new CreditCard());
        paymentMethodList.add(new Gcash("Mau Tuazon", "09171234567"));
        paymentMethodList.add(new CustomPaymentMethod());
        paymentMethodList.add(null);


        paymentMethodList.forEach(paymentMethod -> {

            String type = "null payment method";
            if (paymentMethod instanceof CheckingAccount) {
                type = "Checking Account";
            } else if (paymentMethod instanceof CreditCard) {
                type = "CreditCard";
            } else if (paymentMethod instanceof CustomPaymentMethod) {
                type = "Custom Payment Method";
            } else if (paymentMethod instanceof Gcash) {
                type = "Gcash";
            } else if (paymentMethod instanceof SavingsAccount) {
                type = "Savings Account";
            } else if (paymentMethod instanceof BankAccount) {
                type = "Bank Account";
            }

            if (type == "null payment method") {
                System.out.println(type);
            }

            System.out.println("""
                %s
                %s
                """.formatted(type, paymentMethod.getAccountDetails()));
        });
    }

}
