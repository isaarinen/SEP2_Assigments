package org.example;

import java.util.*;

import static org.example.Calculator.*;

public class Main {
    public static void main(String[] args) {
        if (System.console() == null) {
            System.err.println("ERROR: No interactive console found.");
            System.err.println("Please run this Docker container with the '-it' flags.");
            System.err.println("Example: docker run -it my-java-app");
            System.exit(1);
        }
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter language (en, fi, se, jp):");
        String language = scanner.nextLine();
        Locale local = new Locale(language);
        System.out.println("Current locale: " + local);

        ResourceBundle messages = ResourceBundle.getBundle("messages", local);

        System.out.println(messages.getString("item.count"));
        int itemCount = scanner.nextInt();
        List<Integer> shoppingCart = new ArrayList<>();
        for (int i = 0; i < itemCount; i++) {
            System.out.println(messages.getString("item.price"));
            int price = scanner.nextInt();
            System.out.println(messages.getString("item.quantity"));
            int quantity = scanner.nextInt();
            int totalCost = calculateTotal(price, quantity);
            System.out.println(messages.getString("item.total") + totalCost);
            shoppingCart.add(totalCost);
        }
        int shoppingCartCost = shoppingCartTotal(shoppingCart);
        System.out.println(messages.getString("item.total") + shoppingCartCost);
        scanner.close();
    }
}
