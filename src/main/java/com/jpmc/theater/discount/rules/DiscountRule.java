package com.jpmc.theater.discount.rules;

import com.jpmc.theater.Showing;

import java.math.BigDecimal;

/**
 * Represents a discount rule that is independent of the customer.
 */
public interface DiscountRule {

    BigDecimal calculateEffectivePrice(Showing showing);

    Discount getDiscount();
}
