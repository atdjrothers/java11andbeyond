package com.masters.group.exercise2;

import com.masters.group.exercise1.models.Cart;
import com.masters.group.exercise1.models.Order;
import com.masters.group.exercise1.models.Product;
import com.masters.group.exercise2.utils.PaymentDetails;
import com.masters.group.exercise2.utils.ProductHelper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;

import static com.masters.group.exercise1.utils.Constants.CATEGORY;
import static com.masters.group.exercise1.utils.Constants.STRING_EMPTY;


public class ShoppingCart {

    private static final List<Product> products = new ArrayList<>();
    private static final Map<String, List<Product>> productsMapByCategory = new HashMap<>();
    private static String[] CATEGORIES_KEY;
    private static final Cart cart = new Cart();


    public static void main(String[] args) throws IOException {
        readFile();
        try {
            executeProgram();
        } catch (Exception e) {
            System.out.println("Invalid Option");
            executeProgram();
        }
    }

    private static void executeProgram() {
        Scanner in = new Scanner(System.in);
        int option = 0;
        while (option != -2) {
            displayCategories();
            option = in.nextInt();
            String category = switch(option) {
                case -1 -> {
                    checkout(in);
                    yield STRING_EMPTY;
                }
                case 1 -> CATEGORIES_KEY[1];
                case 2 -> CATEGORIES_KEY[0];
                case 3 -> CATEGORIES_KEY[2];
                default -> STRING_EMPTY;
            };

            if (!category.isBlank()) {
                List<Product> items = displayItems(category);
                addToCart(in, items, category);
            }
        }
    }

    private static void addToCart(Scanner in, List<Product> items, String category) {
        System.out.printf("\nChoose item (-1 to go back to Categories): \n");
        int itemNumber = in.nextInt();
        while(itemNumber != -1){
            System.out.printf("\nEnter How Many:");
            Order order = new Order();
            double quantity = in.nextDouble();
            order.setQuantity(quantity);
            order.setProduct(items.get(itemNumber - 1));
            cart.addOrder(order);
            cart.addToTotalAmount(order.getProduct().getPrice()*quantity);
            System.out.printf("\nItem Added: " + order.displayProduct(order.getProduct(), quantity));
            System.out.printf("\n" + cart.getCartDetails());

            System.out.printf("\nCurrent Items in Cart:\n");
            cart.getOrders().forEach( o -> {
                System.out.println(order.displayProduct(o.getProduct(),o.getQuantity()));
            });

            displayItems(category);
            System.out.printf("\nChoose item (-1 to go back to Categories): \n");
            itemNumber = in.nextInt();
        }

    }

    private static void readFile() throws IOException {
        String filePath = new File("").getAbsolutePath();
        Path filepath = Paths.get("%s/src/main/resources/stocks.csv".formatted(filePath));
        String content = Files.readString(filepath);
        content.lines().filter(Predicate.not(String::isBlank)).forEach(l -> products.add(l.transform(ProductHelper::getProduct)));
        products.forEach(p -> {
            productsMapByCategory.computeIfAbsent(p.getCategory(), k -> new ArrayList<>()).add(p);
        });
        CATEGORIES_KEY = productsMapByCategory.keySet().stream().sorted(String::compareTo).toArray(String[]::new);
    }

    private static List<Product> displayItems(String category) {
        List<Product> items = productsMapByCategory.getOrDefault(category, new ArrayList<>());
        AtomicReference<Integer> ctr = new AtomicReference<>(0);
        System.out.printf("\n\nList of items under %s:\n", category);
        items.forEach(item -> {
            ctr.set(ctr.get() + 1);
            String display = """
                    [%s] %-100s price: %.2f / %s
                    """;
            System.out.print(display.formatted(ctr.get(), item.getName(), item.getPrice(), item.getType()));
        });

        return items;
    }


    private static void displayCategories() {
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

    private static void checkout(Scanner in){
        if (cart.getOrders().isEmpty()) {
            System.out.println("Cart is empty, nothing to checkout.");
        } else {
            PaymentDetails.getPaymentDetail(in, cart);
            cart.getOrders().clear();
        }
    }
}
