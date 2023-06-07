package com.jpmc.theater.services;

import com.jpmc.theater.Showing;
import com.jpmc.theater.discount.rules.DiscountRule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DiscountCalculatorServiceImpl implements DiscountCalculatorService {

    private final List<DiscountRule> discountRules = new ArrayList<>();

    @Override
    public void calculateAndSetEffectivePriceForShowing(Showing showing) {
        showing.setEffectiveMoviePrice(discountRules.stream().map(discountRule -> discountRule.calculateEffectivePrice(showing)).min(BigDecimal::compareTo).get());
    }

    @Override
    public void addDiscountRule(DiscountRule rule) {
        discountRules.add(rule);
    }

}
