package com.jpmc.theater.discount.rules;

import com.jpmc.theater.Movie;
import com.jpmc.theater.Showing;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpecialCodeDiscountTest {

    DiscountRule specialCodeDiscount = new SpecialCodeDiscountRule(1, new Discount(DiscountType.PERCENTAGE, BigDecimal.valueOf(20.0)));
    Movie specialMovie = new Movie("Special code Movie", Duration.ofMinutes(95), BigDecimal.valueOf(12.50), 1);
    Showing specialMovieShowing = new Showing(specialMovie, 1, LocalDateTime.of(2023, 6, 30, 10, 0));
    Movie notSoSpecialMovie = new Movie("Not so special code Movie", Duration.ofMinutes(95), BigDecimal.valueOf(12.50), 2);
    Showing notSoSpecialMovieShowing = new Showing(notSoSpecialMovie, 1, LocalDateTime.of(2023, 6, 30, 10, 0));

    @Test
    public void specialCodeDiscount(){
        BigDecimal bd = specialCodeDiscount.calculateEffectivePrice(specialMovieShowing);
        assertEquals(0, bd.compareTo(BigDecimal.valueOf(10.0)));
    }

    @Test
    public void notSpecialCodeDiscount(){
        BigDecimal bd = specialCodeDiscount.calculateEffectivePrice(notSoSpecialMovieShowing);
        assertEquals(0, bd.compareTo(BigDecimal.valueOf(12.5)));
    }

}
