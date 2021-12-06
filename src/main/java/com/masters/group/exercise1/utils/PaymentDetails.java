package com.masters.group.exercise1.utils;

import com.masters.group.exercise1.models.Cart;

import java.util.Scanner;

public class PaymentDetails {

    public static void getPaymentDetail(Scanner in, Cart cart){
        try {
            System.out.println("""
                1 - Savings
                2 - Checking
                3 - Credit Card
                4 - GCash
                """);

            System.out.println("Choose Payment Method: ");
            int method = in.nextInt();

            String paymentDetail = switch (method) {
                case 1 -> getSavings();
                case 2 -> getChecking();
                case 3 -> getCreditCard();
                case 4 -> getGCash();
                default -> throw new Exception("Invalid");
            };

            System.out.println(paymentDetail.formatted(cart.getCartDetails()));
        } catch(Exception e) {
            getPaymentDetail(in, cart);
        }
    }

    private static String getSavings(){
        return """
               Account Name: Mau Tuazon
               Account Number: 005412345678
               Bank Name: BDO
               %s
               """;
    }

    private static String getChecking(){
        return """
               Account Name: Mau Tuazon
               Account Number: 005412345678
               Bank Name: BDO
               """;
    }

    private static String getCreditCard(){
        return """
               Name on Card: Mau Tuazon
               Credit Card Number: 402123456789012,
               Expiry Date: "12/2022",
               %s
               """;
    }

    private static String getGCash(){
        return """
               Subscriber Name: Mau Tuazon
               Mobile Number: 09171234567
               %s
               """;
    }
}
