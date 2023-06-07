package com.jpmc.theater;

import com.jpmc.theater.services.ReservationService;
import com.jpmc.theater.services.ShowingFormatterService;
import com.jpmc.theater.services.ShowingService;

public class Theater {

    private final ReservationService reservationService;
    private final ShowingService showingService;

    public Theater(ReservationService reservationService, ShowingService showingService) {
        this.reservationService = reservationService;
        this.showingService = showingService;
    }

    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        return reservationService.reserve(customer, sequence, howManyTickets);
    }

    public void printShowings(ShowingFormatterService.Format format) {
        StringBuilder sb = showingService.getFormattedShowings(format);
        System.out.println(sb);
    }

    public void printShowing(ShowingFormatterService.Format format, int sequence) throws IllegalArgumentException {
        showingService.checkSequence(sequence);
        StringBuilder sb = showingService.getFormattedShowing(format, sequence);
        System.out.println(sb);
    }

}
