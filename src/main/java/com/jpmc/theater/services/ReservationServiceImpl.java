package com.jpmc.theater.services;

import com.jpmc.theater.Customer;
import com.jpmc.theater.Reservation;
import com.jpmc.theater.Showing;

public class ReservationServiceImpl implements ReservationService {

    private final ShowingService showingService;

    public ReservationServiceImpl(ShowingService showingService) {
        this.showingService = showingService;
    }

    public Reservation reserve(Customer customer, int sequence, int howManyTickets) throws IllegalArgumentException {
        showingService.checkSequence(sequence);
        Showing showing = showingService.getShowings().get(sequence - 1);
        return new Reservation(customer, showing, howManyTickets);
    }

}
