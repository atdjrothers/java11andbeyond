package com.masters.group.exercise2.utils;

import com.masters.group.exercise1.models.Cart;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static com.masters.group.exercise1.utils.Constants.DATE_FILE_NAME_PATTERN;

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

            String checkoutInfo = paymentDetail.formatted(cart.getCartDetails());
            System.out.println(checkoutInfo);
            writeToFile(checkoutInfo);
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
               %s
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

    private static void writeToFile(String output)  {
        try {
            String filePath = new File("").getAbsolutePath();
            LocalDateTime localDateTime = LocalDateTime.now();
            Path filepath = Paths.get("%s/src/main/resources/checkout-%s.txt"
                    .formatted(filePath, localDateTime.format(DateTimeFormatter.ofPattern(DATE_FILE_NAME_PATTERN))));
            Files.writeString(filepath, output, StandardOpenOption.CREATE_NEW);
        } catch (IOException e) {
            System.out.println("Unable to write to file... " + e.getMessage());
        }
    }
}
