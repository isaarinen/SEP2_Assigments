package org.example;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void calculateTotal() {
        assertEquals(60, Calculator.calculateTotal(15, 4));
    }

    @Test
    void calculateTotal_zero() {
        assertEquals(0, Calculator.calculateTotal(15, 0));
    }

    @Test
    void shoppingCartTotal() {
        assertEquals(55, Calculator.shoppingCartTotal(List.of(20, 15, 20)));
    }

    @Test
    void shoppingCartTotal_zero() {
        assertEquals(0, Calculator.shoppingCartTotal(List.of()));
    }
}