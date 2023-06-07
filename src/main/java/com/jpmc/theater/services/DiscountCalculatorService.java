package com.jpmc.theater.services;

import com.jpmc.theater.Showing;
import com.jpmc.theater.discount.rules.DiscountRule;

public interface DiscountCalculatorService {

    void calculateAndSetEffectivePriceForShowing(Showing showing);

    void addDiscountRule(DiscountRule rule);
}
