package com.jpmc.theater.services;

import com.jpmc.theater.Movie;
import com.jpmc.theater.discount.rules.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TestUtils {

    public static DiscountCalculatorService getInitializedDiscountCalculatorService(){
        DiscountCalculatorService discountCalculatorService = new DiscountCalculatorServiceImpl();
        Discount specialMovieDiscount = new Discount(DiscountType.PERCENTAGE, 20);
        DiscountRule specialMovieDiscountRule = new SpecialCodeDiscountRule(1, specialMovieDiscount);
        discountCalculatorService.addDiscountRule(specialMovieDiscountRule);

        Discount firstMovieOfTheDayDiscount = new Discount(DiscountType.ABSOLUTE, 3);
        DiscountRule firstMovieOfTheDayDiscountRule = new SequenceOfTheDayDiscountRule(1, firstMovieOfTheDayDiscount);
        discountCalculatorService.addDiscountRule(firstMovieOfTheDayDiscountRule);

        Discount secondMovieOfTheDayDiscount = new Discount(DiscountType.ABSOLUTE, 2);
        DiscountRule secondMovieOfTheDayDiscountRule = new SequenceOfTheDayDiscountRule(2, secondMovieOfTheDayDiscount);
        discountCalculatorService.addDiscountRule(secondMovieOfTheDayDiscountRule);

        Discount elevenAMtoFourPMMovieDiscount = new Discount(DiscountType.PERCENTAGE, 25);
        DiscountRule elevenAMtoFourPMMovieDiscountRule = new PeriodOfTheDayDiscountRule(LocalTime.of(11, 0, 0), LocalTime.of(16, 0, 0), elevenAMtoFourPMMovieDiscount);
        discountCalculatorService.addDiscountRule(elevenAMtoFourPMMovieDiscountRule);

        Discount seventhOfTheMonthMovieDiscount = new Discount(DiscountType.ABSOLUTE, 1);
        DiscountRule seventhOfTheMonthMovieDiscountRule = new DayOfMonthDiscountRule(7, seventhOfTheMonthMovieDiscount);
        discountCalculatorService.addDiscountRule(seventhOfTheMonthMovieDiscountRule);

        return discountCalculatorService;
    }

    public static void initShowings(ShowingService showingService){
        Movie spiderMan =  new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), BigDecimal.valueOf(12.5), 1);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), BigDecimal.valueOf(11), 0);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), BigDecimal.valueOf(9), 0);
        showingService.addShowing(turningRed, LocalDateTime.of(2023, 6, 5, 9, 0));
        showingService.addShowing(spiderMan, LocalDateTime.of(2023, 6, 5, 11, 0));
        showingService.addShowing(theBatMan, LocalDateTime.of(2023, 6, 5, 12, 50));
        showingService.addShowing(turningRed, LocalDateTime.of(2023, 6, 5, 14, 30));
        showingService.addShowing(spiderMan, LocalDateTime.of(2023, 6, 5, 16, 10));
        showingService.addShowing(theBatMan, LocalDateTime.of(2023, 6, 5, 17, 50));
        showingService.addShowing(turningRed, LocalDateTime.of(2023, 6, 5, 19, 30));
        showingService.addShowing(spiderMan, LocalDateTime.of(2023, 6, 5, 21, 10));
        showingService.addShowing(theBatMan, LocalDateTime.of(2023, 6, 5, 23, 0));
    }

}
