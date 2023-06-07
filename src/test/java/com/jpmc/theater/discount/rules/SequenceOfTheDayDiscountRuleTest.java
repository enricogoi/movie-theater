package com.jpmc.theater.discount.rules;

import com.jpmc.theater.Movie;
import com.jpmc.theater.Showing;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class SequenceOfTheDayDiscountRuleTest {

    DiscountRule sequenceOfTheDayDiscountRule = new SequenceOfTheDayDiscountRule(1, new Discount(DiscountType.ABSOLUTE, BigDecimal.valueOf(3)));

    @Test
    public void discount(){
        Movie movie = new Movie("test", Duration.ofMinutes(95), BigDecimal.valueOf(12.50), 1);
        Showing showing = new Showing(movie, 1, LocalDateTime.of(2023, 6, 30, 10, 0));
        BigDecimal effectivePrice = sequenceOfTheDayDiscountRule.calculateEffectivePrice(showing);
        assertEquals(0, effectivePrice.compareTo(BigDecimal.valueOf(9.5)));
    }

    @Test
    public void doNotDiscount(){
        Movie movie = new Movie("test", Duration.ofMinutes(95), BigDecimal.valueOf(12.50), 1);
        Showing showing = new Showing(movie, 2, LocalDateTime.of(2023, 6, 8, 10, 0));
        BigDecimal effectivePrice = sequenceOfTheDayDiscountRule.calculateEffectivePrice(showing);
        assertEquals(0, effectivePrice.compareTo(BigDecimal.valueOf(12.50)));
    }

    @Test
    public void discountAndFloor(){
        Movie movie = new Movie("test", Duration.ofMinutes(95), BigDecimal.valueOf(2.50), 1);
        Showing showing = new Showing(movie, 1, LocalDateTime.of(2023, 6, 30, 10, 0));
        BigDecimal effectivePrice = sequenceOfTheDayDiscountRule.calculateEffectivePrice(showing);
        assertEquals(0, effectivePrice.compareTo(BigDecimal.ZERO));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, -Integer.MAX_VALUE})
    public void testSequenceOfTheDayDiscountRuleInvalidSequence(int sequence){
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new SequenceOfTheDayDiscountRule(sequence, new Discount(DiscountType.ABSOLUTE, BigDecimal.valueOf(3))),
                "Constructor was expected to throw an IllegalArgumentException, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Sequence of the day cannot be less than 1"));
    }

    @Test
    public void testDayOfMonthDiscountRuleConstructorNegativeDiscount(){
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new SequenceOfTheDayDiscountRule(1, new Discount(DiscountType.ABSOLUTE, BigDecimal.valueOf(-1.0))),
                "Constructor was expected to throw an IllegalArgumentException, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Discount cannot be less than zero"));
    }

}
