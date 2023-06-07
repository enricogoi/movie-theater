package com.jpmc.theater;

import java.math.BigDecimal;

public class Reservation {
    private final Customer customer;
    private final Showing showing;
    private final int audienceCount;

    public Reservation(Customer customer, Showing showing, int audienceCount) {
        this.customer = customer;
        this.showing = showing;
        this.audienceCount = audienceCount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Showing getShowing() {
        return showing;
    }

    public int getAudienceCount() {
        return audienceCount;
    }

    public BigDecimal getTotalFeeForCustomer() {
        return showing.getEffectiveMoviePrice().multiply(BigDecimal.valueOf(getAudienceCount()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        if (audienceCount != that.audienceCount) return false;
        if (!customer.equals(that.customer)) return false;
        return showing.equals(that.showing);
    }

    @Override
    public int hashCode() {
        int result = customer.hashCode();
        result = 31 * result + showing.hashCode();
        result = 31 * result + audienceCount;
        return result;
    }
}