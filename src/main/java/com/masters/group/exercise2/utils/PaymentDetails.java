package com.masters.group.exercise2.utils;

import com.masters.group.exercise1.models.Cart;
import com.masters.group.exercise2.models.CheckingAccount;
import com.masters.group.exercise2.models.CreditCard;
import com.masters.group.exercise2.models.Gcash;
import com.masters.group.exercise2.models.SavingsAccount;

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

    record AccountInfo(String accountName, String accountNumber, String bankName){}

    public static void displayPaymentMethods(Scanner in, Cart cart) {
        try {
            int ctr = -1;
            System.out.println("""
                [%d] - Savings Account
                %s
                [%d] - Checking Account
                %s
                [%d] - Credit Card
                %s
                [%d] - GCash
                %s
                [%d] - COD
                [%d] - Pay with other account
                """.formatted(++ctr, getSavingsMau().formatted(""), ++ctr, getCheckingMau().formatted(""), ++ctr,
                    getCreditCardMau().formatted(""), ++ctr, getGCashMau().formatted(""), ++ctr, ++ctr));

            System.out.println("Choose Payment Method: ");
            int method = in.nextInt();

            String paymentDetail = switch (method) {
                case 0 -> getSavingsMau();
                case 1 -> getCheckingMau();
                case 2 -> getCreditCardMau();
                case 3 -> getGCashMau();
                case 4 -> getCOD();
                case 5 -> payWithOtherAccount(in);
                default -> throw new Exception("Invalid");
            };

            String checkoutInfo = paymentDetail.concat("\n").concat(cart.getCartDetails());
            System.out.println(checkoutInfo);
            writeToFile(checkoutInfo);
        } catch(Exception e) {
            displayPaymentMethods(in, cart);
        }
    }

    public static String payWithOtherAccount(Scanner in){
        String output = "";
        try {
            System.out.println("""
                1 - Savings
                2 - Checking
                3 - Credit Card
                4 - GCash
                """);


            System.out.println("Choose Payment Method: ");
            int method = in.nextInt();

            Object otherPayment = switch (method) {
                case 1, 2 -> getAccountInfo(in);
                case 3 -> getCreditCardOther(in);
                case 4 -> getGCashOther(in);
                default -> throw new Exception("Invalid");
            };

            if (otherPayment instanceof AccountInfo savingsInfo && method == 1) {
                SavingsAccount savingsAccount = new SavingsAccount(savingsInfo.accountName, savingsInfo.accountNumber, savingsInfo.bankName);
                output = savingsAccount.getAccountDetails();
            } else if (otherPayment instanceof AccountInfo checkingInfo) {
                CheckingAccount checkingAccount = new CheckingAccount(checkingInfo.accountName, checkingInfo.accountNumber, checkingInfo.bankName);
                output = checkingAccount.getAccountDetails();
            } else {
                output = (String) otherPayment;
            }

        } catch(Exception e) {
            payWithOtherAccount(in);
        }

        return output;
    }

    private static String getSavingsMau(){
        return new SavingsAccount("Mau Tuazon", "005412345678", "BDO", "Mau savings").getAccountDetails();
    }

    private static String getCheckingMau(){
        return new SavingsAccount("Mau Tuazon", "005412345678", "BDO", "Mau checking").getAccountDetails();
    }

    private static String getCreditCardMau(){
        return new CreditCard("Mau Tuazon", "402123456789012", "12/2025", "Mau credit card").getAccountDetails();
    }

    private static String getGCashMau(){
        return new Gcash("Mau Tuazon", "09171234567", "Mau GCash").getAccountDetails();
    }

    private static String getCOD(){
        return """
               Thank you for shopping.
               You have selected to pay COD.
               Please prepare the amount below:
               COD
               """;
    }

    private static AccountInfo getAccountInfo(Scanner in) {
        System.out.println("Enter Account Info");
        System.out.println("Enter Account Name: ");
        String accountName = in.next();

        System.out.println("Enter Account number: ");
        String accountNumber = in.next();

        System.out.println("Enter Bank Name: ");
        String bankName = in.next();

        return new AccountInfo(accountName, accountNumber, bankName);
    }

    private static String getCreditCardOther(Scanner in) {
        System.out.println("Enter Account Info");
        System.out.println("Enter Name on card: ");
        String accountName = in.next();

        System.out.println("Enter Credit card number: ");
        String accountNumber = in.next();

        System.out.println("Enter Expiry date: ");
        String expiryDate = in.next();

        return new CreditCard(accountName, accountNumber, expiryDate).getAccountDetails();
    }

    private static String getGCashOther(Scanner in) {
        System.out.println("Enter Account Info");
        System.out.println("Enter Name on card: ");
        String subscriberName = in.next();

        System.out.println("Enter Credit card number: ");
        String mobileNumber = in.next();

        return new Gcash(subscriberName, mobileNumber).getAccountDetails();
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
