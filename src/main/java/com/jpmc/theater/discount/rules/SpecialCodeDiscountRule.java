package com.jpmc.theater.discount.rules;

import com.jpmc.theater.Movie;
import com.jpmc.theater.Showing;

import java.math.BigDecimal;

public class SpecialCodeDiscountRule implements DiscountRule {

    private final int movieCodeSpecial;
    private final Discount discount;

    public SpecialCodeDiscountRule(int movieCodeSpecial, Discount discount) throws IllegalArgumentException, NullPointerException {
        this.movieCodeSpecial = movieCodeSpecial;
        this.discount = discount;
    }

    @Override
    public BigDecimal calculateEffectivePrice(Showing showing) {
        final Movie movie = showing.getMovie();
        return movie.getSpecialCode() == movieCodeSpecial ? getDiscount().discountAndFloor(movie.getBaseTicketPrice()) : movie.getBaseTicketPrice();
    }

    @Override
    public Discount getDiscount() {
        return this.discount;
    }

}
