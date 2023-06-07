package com.jpmc.theater.discount.rules;

import com.jpmc.theater.Showing;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Objects;

public class PeriodOfTheDayDiscountRule implements DiscountRule {

    private final LocalTime periodStart;
    private final LocalTime periodEnd;
    private final Discount discount;

    public PeriodOfTheDayDiscountRule(LocalTime periodStart, LocalTime periodEnd, Discount discount) throws IllegalArgumentException, NullPointerException {
        checkParameters(periodStart, periodEnd);
        this.discount = discount;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
    }

    @Override
    public BigDecimal calculateEffectivePrice(Showing showing) {
        LocalTime showingStartTime = showing.getStartTime().toLocalTime();

        if (showingStartTime.compareTo(periodStart) >= 0 && showingStartTime.isBefore(periodEnd)){
            return getDiscount().discountAndFloor(showing.getMovie().getBaseTicketPrice());
        }

        return showing.getMovie().getBaseTicketPrice();
    }

    @Override
    public Discount getDiscount() {
        return discount;
    }

    private void checkParameters(LocalTime periodStart, LocalTime periodEnd) throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(periodStart, "periodStart cannot be null");
        Objects.requireNonNull(periodEnd, "periodEnd cannot be null");

        if (periodStart.isAfter(periodEnd)){
            throw new IllegalArgumentException("periodStart cannot be after periodEnd");
        }
    }
}
