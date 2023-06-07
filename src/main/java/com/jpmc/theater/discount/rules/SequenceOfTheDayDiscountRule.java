package com.jpmc.theater.discount.rules;

import com.jpmc.theater.Showing;

import java.math.BigDecimal;

public class SequenceOfTheDayDiscountRule implements DiscountRule {

    private final int sequence;
    private final Discount discount;

    public SequenceOfTheDayDiscountRule(int sequence, Discount discount) throws IllegalArgumentException {
        checkParameters(sequence);
        this.sequence = sequence;
        this.discount = discount;
    }

    @Override
    public BigDecimal calculateEffectivePrice(Showing showing) {
        if (showing.getSequenceOfTheDay() != sequence){
            return showing.getMovie().getBaseTicketPrice();
        }

        return getDiscount().discountAndFloor(showing.getMovie().getBaseTicketPrice());
    }

    @Override
    public Discount getDiscount() {
        return discount;
    }

    private void checkParameters(int sequence) throws IllegalArgumentException {
        if (sequence < 1) {
            throw new IllegalArgumentException("Sequence of the day cannot be less than 1");
        }
    }

}
