package com.jpmc.theater.services;

import com.jpmc.theater.Movie;
import com.jpmc.theater.Showing;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import static com.jpmc.theater.services.TestUtils.getInitializedDiscountCalculatorService;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountCalculatorServiceTest {

    private DiscountCalculatorService discountCalculatorService = getInitializedDiscountCalculatorService();

    @Test
    public void maxDiscountIsSpecialMovie(){
        Movie m = new Movie("test", Duration.ofMinutes(90), BigDecimal.valueOf(20), 1);
        Showing s = new Showing(m, 3, LocalDateTime.of(2023, 5, 6, 10, 0 ,0));
        discountCalculatorService.calculateAndSetEffectivePriceForShowing(s);
        assertEquals(0, s.getEffectiveMoviePrice().compareTo(BigDecimal.valueOf(16)));
    }

    @Test
    public void maxDiscountIsFirstOfDay(){
        Movie m = new Movie("test", Duration.ofMinutes(90), BigDecimal.valueOf(10), 1);
        Showing s = new Showing(m, 1, LocalDateTime.of(2023, 5, 6, 10, 0 ,0));
        discountCalculatorService.calculateAndSetEffectivePriceForShowing(s);
        assertEquals(0, s.getEffectiveMoviePrice().compareTo(BigDecimal.valueOf(7)));
    }

    @Test
    public void maxDiscountIsSecondOfDay(){
        Movie m = new Movie("test", Duration.ofMinutes(90), BigDecimal.valueOf(8), 1);
        Showing s = new Showing(m, 2, LocalDateTime.of(2023, 5, 6, 10, 0 ,0));
        discountCalculatorService.calculateAndSetEffectivePriceForShowing(s);
        assertEquals(0, s.getEffectiveMoviePrice().compareTo(BigDecimal.valueOf(6)));
    }

    @Test
    public void maxDiscountIsPeriodOfDay(){
        Movie m = new Movie("test", Duration.ofMinutes(90), BigDecimal.valueOf(10), 1);
        Showing s = new Showing(m, 2, LocalDateTime.of(2023, 5, 6, 12, 0 ,0));
        discountCalculatorService.calculateAndSetEffectivePriceForShowing(s);
        assertEquals(0, s.getEffectiveMoviePrice().compareTo(BigDecimal.valueOf(7.5)));
    }

    @Test
    public void maxDiscountIsSeventhOfMonth(){
        Movie m = new Movie("test", Duration.ofMinutes(90), BigDecimal.valueOf(3), 3);
        Showing s = new Showing(m, 3, LocalDateTime.of(2023, 5, 7, 12, 0 ,0));
        discountCalculatorService.calculateAndSetEffectivePriceForShowing(s);
        assertEquals(0, s.getEffectiveMoviePrice().compareTo(BigDecimal.valueOf(2)));
    }
}
