package com.jpmc.theater.discount.rules;

import com.jpmc.theater.Movie;
import com.jpmc.theater.Showing;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class PeriodOfTheDayDiscountRuleTest {

    private final LocalTime periodStart = LocalTime.of( 11, 0, 0);
    private final LocalTime periodEnd = LocalTime.of( 16, 0, 0);
    private final LocalTime periodEndInvalid = LocalTime.of( 10, 0, 0);
    DiscountRule periodOfTheDayDiscountRule = new PeriodOfTheDayDiscountRule(periodStart, periodEnd, new Discount(DiscountType.PERCENTAGE, BigDecimal.valueOf(25)));

    @Test
    public void discountBecauseStartTimeIsWithinInterval(){
        Movie movie = new Movie("test", Duration.ofMinutes(95), BigDecimal.valueOf(12.50), 1);
        Showing showing = new Showing(movie, 1, LocalDateTime.of(2023, 6, 30, 12, 0));
        BigDecimal effectivePrice = periodOfTheDayDiscountRule.calculateEffectivePrice(showing);
        assertEquals(0, effectivePrice.compareTo(BigDecimal.valueOf(9.38)));
    }

    @Test
    public void discountBecauseStartTimeIsAtPeriodStart(){
        Movie movie = new Movie("test", Duration.ofMinutes(95), BigDecimal.valueOf(12.50), 1);
        Showing showing = new Showing(movie, 1, LocalDateTime.of(2023, 6, 30, 11, 0));
        BigDecimal effectivePrice = periodOfTheDayDiscountRule.calculateEffectivePrice(showing);
        assertEquals(0, effectivePrice.compareTo(BigDecimal.valueOf(9.38)));
    }

    @Test
    public void noDiscountBecauseStartTimeIsEarlier(){
        Movie movie = new Movie("test", Duration.ofMinutes(95), BigDecimal.valueOf(12.50), 1);
        Showing showing = new Showing(movie, 1, LocalDateTime.of(2023, 6, 30, 10, 0));
        BigDecimal effectivePrice = periodOfTheDayDiscountRule.calculateEffectivePrice(showing);
        assertEquals(0, effectivePrice.compareTo(BigDecimal.valueOf(12.50)));
    }

    @Test
    public void noDiscountBecauseStartTimeIsAfter(){
        Movie movie = new Movie("test", Duration.ofMinutes(95), BigDecimal.valueOf(12.50), 1);
        Showing showing = new Showing(movie, 1, LocalDateTime.of(2023, 6, 30, 17, 0));
        BigDecimal effectivePrice = periodOfTheDayDiscountRule.calculateEffectivePrice(showing);
        assertEquals(0, effectivePrice.compareTo(BigDecimal.valueOf(12.50)));
    }

    @Test
    public void noDiscountBecauseStartTimeIsAtPeriodEnd(){
        Movie movie = new Movie("test", Duration.ofMinutes(95), BigDecimal.valueOf(12.50), 1);
        Showing showing = new Showing(movie, 1, LocalDateTime.of(2023, 6, 30, 16, 0));
        BigDecimal effectivePrice = periodOfTheDayDiscountRule.calculateEffectivePrice(showing);
        assertEquals(0, effectivePrice.compareTo(BigDecimal.valueOf(12.50)));
    }

    @Test
    public void periodOfTheDayDiscountRuleNullPeriodStart(){
        NullPointerException thrown = assertThrows(
                NullPointerException.class,
                () -> new PeriodOfTheDayDiscountRule(null, periodEnd, new Discount(DiscountType.PERCENTAGE, BigDecimal.valueOf(25))),
                "Constructor was expected to throw an IllegalArgumentException, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("periodStart cannot be null"));
    }

    @Test
    public void periodOfTheDayDiscountRuleNullPeriodEnd(){
        NullPointerException thrown = assertThrows(
                NullPointerException.class,
                () -> new PeriodOfTheDayDiscountRule(periodStart, null, new Discount(DiscountType.PERCENTAGE, BigDecimal.valueOf(25))),
                "Constructor was expected to throw an IllegalArgumentException, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("periodEnd cannot be null"));
    }

    @Test
    public void periodOfTheDayDiscountRuleInvalidPeriod(){
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new PeriodOfTheDayDiscountRule(periodStart, periodEndInvalid, new Discount(DiscountType.PERCENTAGE, BigDecimal.valueOf(25))),
                "Constructor was expected to throw an IllegalArgumentException, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("periodStart cannot be after periodEnd"));
    }

}
