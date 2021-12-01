package com.masters.group.exercise1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static com.masters.group.exercise1.utils.Constants.*;

public class ShoppingCart {

    public static void main(String[] args){
        try {
            Scanner in = new Scanner(System.in);
            readFile();
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

    private static void readFile() throws IOException {
        String filePath = new File("").getAbsolutePath();
        Path filepath = Paths.get("%s/src/main/resources/stocks.csv".formatted(filePath));
        String content = Files.readString(filepath);
        content.lines().forEach(System.out::println);
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
