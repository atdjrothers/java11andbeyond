package com.masters.twelve;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exercise2 {
    private static final NumberFormat fmt = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
    private static final Stream<Integer> intStream = Stream.of(100, 1000, 100000, 1000000);
    private static final String[] OPTIONS = { "Savings and Checking", "Credit Card", "GCash" };

    public static void main(String[] args){
        fmt.setMinimumFractionDigits(3);
        startProgram();
    }

    private static void startProgram(){
        Scanner in = new Scanner(System.in);
        try {
            displayOptions();
            if(in.hasNext()) {
                int option = in.nextInt();
                System.out.printf("You selected %s\n\n", OPTIONS[option - 1]);
                String totalPayable = getTotalPayableString();
                String output = switch (option) {
                    case 1 -> getSavingsAndChecking(totalPayable);
                    case 2 -> getCreditCard(totalPayable);
                    case 3 -> getGCash(totalPayable);
                    default -> throw new Exception("Invalid Option");
                };

                System.out.println(output);
            }
        } catch(Exception e) {
            System.out.println("Invalid Option");
            startProgram();
        }
    }

    private static void displayOptions(){
        String optionDisplay = """
                Please select number:
                [1] - %s
                [2] - %s
                [3] - %s
                """;
        int i = -1;
        System.out.println(optionDisplay.formatted(OPTIONS[++i], OPTIONS[++i], OPTIONS[++i]));
    }

    private static String getTotalPayableString(){
        String totalPayableString = """
                {
                    \t"totalAmount": %.5f
                    \t"totalAmount compact: %s
                    \t"number of items": %d
                \t}""";
        var totalPayable = intStream
                        .collect(Collectors.teeing(
                                Collectors.counting(),
                                Collectors.summingInt(n -> n),
                                (count, sum)  -> new TotalPayable(Math.toIntExact(count), sum)
                        ));

        double total = totalPayable.getTotal();
        totalPayableString = totalPayableString.formatted(total, fmt.format(total), totalPayable.getCount());
        return totalPayableString;
    }

    private static String getSavingsAndChecking(String totalPayable){
        String paymentDetails = """
                {
                    "accountName": "Mau Tuazon",
                    "accountNumber": "005412345678",
                    "bankName": "BDO",
                    "totalPayableAsString":
                    %s
                }
                """;
        return paymentDetails.formatted(totalPayable);
    }

    private static String getCreditCard(String totalPayable){
        String paymentDetails = """
                {
                    "nameOnCard": "Mau Tuazon",
                    "creditCardNumber": "402123456789012",
                    "expiryDate": "12/2022",
                    "totalPayableAsString":
                    %s
                }
                """;
        return paymentDetails.formatted(totalPayable);
    }

    private static String getGCash(String totalPayable){
        String paymentDetails = """
                {
                    "accountName": "Mau Tuazon",
                    "mobileNumber": "09171234567",
                    "totalPayableAsString":
                    %s
                }
                """;
        return paymentDetails.formatted(totalPayable);
    }
}
