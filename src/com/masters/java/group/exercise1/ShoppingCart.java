package com.masters.java.group.exercise1;

import java.util.Scanner;

import static com.masters.java.group.exercise1.utils.Constants.*;

public class ShoppingCart {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        try {
            int option = 0;
            while(option != -2) {
                displayCategories();
                option = in.nextInt();
            }
        } catch(Exception e) {
            System.out.println("Invalid Option");
            displayCategories();
        }
    }


    private static void displayCategories(){
        String optionDisplay = """
                Category:
                [1] - %s
                [2] - %s
                [3] - %s
                """;
        int i = -1;
        System.out.println(optionDisplay.formatted(CATEGORY[++i], CATEGORY[++i], CATEGORY[++i]));
        System.out.println("Choose Category (-1 to Checkout, -2 to Exit):");
    }
}
