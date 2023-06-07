package com.jpmc.theater.services;

import com.jpmc.theater.Customer;
import com.jpmc.theater.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.jpmc.theater.services.TestUtils.getInitializedDiscountCalculatorService;
import static com.jpmc.theater.services.TestUtils.initShowings;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationServiceTest {

    private ShowingService showingService;
    private ReservationService reservationService;

    @BeforeEach
    public void initTestContext(){
        showingService = new ShowingServiceImpl(getInitializedDiscountCalculatorService(), new ShowingFormatterServiceImpl());
        reservationService = new ReservationServiceImpl(showingService);
        initShowings(showingService);
    }

    @Test
    public void reserve(){
        Customer customer = new Customer("customer name", "customerId");
        Reservation reservation = reservationService.reserve(customer, 2, 4);
        assertEquals(4, reservation.getAudienceCount());
        assertEquals(0, BigDecimal.valueOf(37.52).compareTo(reservation.getTotalFeeForCustomer()));
    }
}
