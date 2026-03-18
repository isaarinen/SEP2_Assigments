package org.example;

import java.util.*;

public class Calculator {
    public static int calculateTotal(int price, int quantity) {
        return price * quantity;
    }
    public static int shoppingCartTotal(List<Integer> shoppingCart) {
        int total = 0;
        for (int item : shoppingCart) {
            total +=  item;
        }
        return total;
    }
}