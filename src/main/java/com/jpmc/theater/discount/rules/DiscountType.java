package com.jpmc.theater.discount.rules;

public enum DiscountType {
    ABSOLUTE,
    PERCENTAGE;

    static DiscountType getDefault() {
        return ABSOLUTE;
    }
}
