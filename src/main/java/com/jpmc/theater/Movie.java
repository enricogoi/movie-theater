package com.jpmc.theater;

import java.math.BigDecimal;

import java.time.Duration;

import static com.jpmc.theater.Utils.scaleAndRound;

public class Movie {

    private final String title;
    private final Duration runningTime;
    private final BigDecimal baseTicketPrice;
    private final int specialCode;

    public Movie(String title, Duration runningTime, BigDecimal ticketPrice, int specialCode) throws IllegalArgumentException, NullPointerException {
        this.title = title;
        this.runningTime = runningTime;
        checkBaseTicketPrice(ticketPrice);
        this.baseTicketPrice = scaleAndRound(ticketPrice);
        this.specialCode = specialCode;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public BigDecimal getBaseTicketPrice() {
        return baseTicketPrice;
    }

    public int getSpecialCode() {
        return specialCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return title.equals(movie.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    private void checkBaseTicketPrice(BigDecimal baseTicketPrice) throws IllegalArgumentException, NullPointerException {

        if (baseTicketPrice == null){
            throw new NullPointerException("Base ticket price cannot be null");
        }

        if (baseTicketPrice.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Base ticket price " + baseTicketPrice + "must be strictly positive");
        }
    }
}