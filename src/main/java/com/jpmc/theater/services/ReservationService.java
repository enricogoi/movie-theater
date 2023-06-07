package com.jpmc.theater.services;

import com.jpmc.theater.Customer;
import com.jpmc.theater.Reservation;

public interface ReservationService {

    Reservation reserve(Customer customer, int sequence, int howManyTickets);
}
