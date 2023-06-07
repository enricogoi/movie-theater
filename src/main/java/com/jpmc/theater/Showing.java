package com.jpmc.theater;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Showing {

    private final Movie movie;
    private final int sequenceOfTheDay;
    private final LocalDateTime showStartTime;
    private BigDecimal effectiveMoviePrice;

    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public BigDecimal getEffectiveMoviePrice() {
        return effectiveMoviePrice;
    }

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    public void setEffectiveMoviePrice(BigDecimal effectiveMoviePrice){
        this.effectiveMoviePrice = effectiveMoviePrice;
    }
}
