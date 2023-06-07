package com.jpmc.theater.discount.rules;

import java.math.BigDecimal;
import java.util.Objects;

import static com.jpmc.theater.Utils.scaleAndRound;

public class Discount {

    private final static String DISCOUNT_NULL_POINTER_EXCEPTION_MESSAGE = "Discount cannot be null";
    private final static String DISCOUNT_EXCEPTION_MESSAGE = "Discount cannot be less than zero";
    private final static BigDecimal HUNDRED = BigDecimal.valueOf(100l);

    private final DiscountType discountType;
    private final BigDecimal discount;


    public Discount(DiscountType discountType, BigDecimal discount) throws IllegalArgumentException, NullPointerException {
        checkDiscount(discount);
        this.discountType = discountType;
        this.discount = discount;
    }

    public Discount(DiscountType discountType, double discount) throws IllegalArgumentException, NullPointerException {
        BigDecimal bdDiscount = BigDecimal.valueOf(discount);
        checkDiscount(bdDiscount);
        this.discountType = discountType;
        this.discount = bdDiscount;
    }

    public Discount(DiscountType discountType, long discount) throws IllegalArgumentException, NullPointerException {
        BigDecimal bdDiscount = BigDecimal.valueOf(discount);
        checkDiscount(bdDiscount);
        this.discountType = discountType;
        this.discount = bdDiscount;
    }

    public BigDecimal discountAndFloor(BigDecimal original){
        BigDecimal discountedPrice = null;

        switch (discountType){
            case PERCENTAGE:
                BigDecimal remainingPercentage = HUNDRED.subtract(discount).divide(HUNDRED);
                discountedPrice = scaleAndRound(original.multiply(remainingPercentage));
                break;
            case ABSOLUTE:
                discountedPrice = scaleAndRound(original.subtract(discount));
                break;
        }

        return discountedPrice != null && discountedPrice.compareTo(BigDecimal.ZERO) > 0 ? discountedPrice : BigDecimal.ZERO;
    }

    public void checkDiscount(BigDecimal discount) throws IllegalArgumentException, NullPointerException {

        Objects.requireNonNull(discount, DISCOUNT_NULL_POINTER_EXCEPTION_MESSAGE);

        if (discount.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException(DISCOUNT_EXCEPTION_MESSAGE);
        }
    }
}
