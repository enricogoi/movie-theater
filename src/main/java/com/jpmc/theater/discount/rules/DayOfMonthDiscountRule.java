package com.jpmc.theater.discount.rules;

import com.jpmc.theater.Showing;

import java.math.BigDecimal;
import java.time.Year;

public class DayOfMonthDiscountRule implements DiscountRule {

    private final int dayOfMonth;
    private final Discount discount;

    public DayOfMonthDiscountRule(int dayOfMonth, Discount discount) throws IllegalArgumentException {
        checkParameters(dayOfMonth);
        this.dayOfMonth = dayOfMonth;
        this.discount = discount;
    }

    @Override
    public BigDecimal calculateEffectivePrice(Showing showing) {

        if (checkValidRuleForThisMonth(showing) && dayOfMonth == showing.getStartTime().getDayOfMonth()){
            return getDiscount().discountAndFloor(showing.getMovie().getBaseTicketPrice());
        }

        return showing.getMovie().getBaseTicketPrice();

    }

    @Override
    public Discount getDiscount() {
        return discount;
    }

    private void checkParameters(int dayOfMonth) throws IllegalArgumentException {
        if (dayOfMonth < 1 || dayOfMonth > 31){
            throw new IllegalArgumentException("Day of month must be between 1 and 31 included");
        }
    }

    private boolean checkValidRuleForThisMonth(Showing showing){
        switch (showing.getStartTime().getMonth()){
            case JANUARY:
            case MARCH:
            case MAY:
            case JULY:
            case AUGUST:
            case OCTOBER:
            case DECEMBER:
                return (dayOfMonth >= 1 && dayOfMonth <= 31);
            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                return (dayOfMonth >= 1 && dayOfMonth <= 30);
            case FEBRUARY:
                return Year.of(showing.getStartTime().getYear()).isLeap() ? (dayOfMonth >= 1 && dayOfMonth <= 29) : (dayOfMonth >= 1 && dayOfMonth <= 28);
        }

        return false;
    }
}
