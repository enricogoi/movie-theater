package com.jpmc.theater.discount.rules;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiscountTest {

    @Test
    public void discountConstructorNegativeDiscount(){
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Discount(DiscountType.ABSOLUTE, BigDecimal.valueOf(-10.0)),
                "Constructor was expected to throw an IllegalArgumentException, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Discount cannot be less than zero"));
    }

    @Test
    public void discountConstructorNullDiscount(){
        NullPointerException thrown = assertThrows(
                NullPointerException.class,
                () -> new Discount(DiscountType.ABSOLUTE,null),
                "Constructor was expected to throw a NullPointerException, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Discount cannot be null"));
    }
}
