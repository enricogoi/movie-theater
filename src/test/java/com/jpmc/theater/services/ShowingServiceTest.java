package com.jpmc.theater.services;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.jpmc.theater.services.TestUtils.getInitializedDiscountCalculatorService;
import static com.jpmc.theater.services.TestUtils.initShowings;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowingServiceTest {


    private DiscountCalculatorService discountCalculatorService = getInitializedDiscountCalculatorService();
    private ShowingService showingService = new ShowingServiceImpl(discountCalculatorService, new ShowingFormatterServiceImpl());

    @Test
    public void addShowing(){
        initShowings(showingService);

        assertEquals(9, showingService.getMaxSequence());
        assertEquals(0, showingService.getShowings().get(0).getEffectiveMoviePrice().compareTo(BigDecimal.valueOf(8)));
        assertEquals(0, showingService.getShowings().get(1).getEffectiveMoviePrice().compareTo(BigDecimal.valueOf(9.38)));
        assertEquals(0, showingService.getShowings().get(2).getEffectiveMoviePrice().compareTo(BigDecimal.valueOf(6.75)));
        assertEquals(0, showingService.getShowings().get(3).getEffectiveMoviePrice().compareTo(BigDecimal.valueOf(8.25)));
        assertEquals(0, showingService.getShowings().get(4).getEffectiveMoviePrice().compareTo(BigDecimal.valueOf(10)));
        assertEquals(0, showingService.getShowings().get(5).getEffectiveMoviePrice().compareTo(BigDecimal.valueOf(9)));
        assertEquals(0, showingService.getShowings().get(6).getEffectiveMoviePrice().compareTo(BigDecimal.valueOf(11)));
        assertEquals(0, showingService.getShowings().get(7).getEffectiveMoviePrice().compareTo(BigDecimal.valueOf(10)));
        assertEquals(0, showingService.getShowings().get(8).getEffectiveMoviePrice().compareTo(BigDecimal.valueOf(9)));
    }

}
