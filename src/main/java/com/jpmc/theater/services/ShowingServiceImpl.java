package com.jpmc.theater.services;

import com.jpmc.theater.Movie;
import com.jpmc.theater.Showing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShowingServiceImpl implements ShowingService {

    private final List<Showing> showings = new ArrayList<>();
    private final DiscountCalculatorService discountCalculatorService;
    private final ShowingFormatterService showingFormatterService;

    public ShowingServiceImpl(DiscountCalculatorService discountCalculatorService, ShowingFormatterService showingFormatterService) {
        this.discountCalculatorService = discountCalculatorService;
        this.showingFormatterService = showingFormatterService;
    }

    @Override
    public List<Showing> getShowings() {
        return Collections.unmodifiableList(showings);
    }

    @Override
    public void addShowing(Movie movie, LocalDateTime startTime) {
        Showing s = new Showing(movie, showings.size() + 1, startTime);
        discountCalculatorService.calculateAndSetEffectivePriceForShowing(s);
        showings.add(s);
    }

    @Override
    public int getMaxSequence() {
        return showings.size();
    }

    @Override
    public void checkSequence(int sequence) throws IllegalArgumentException {
        if (sequence < 1 || sequence > getMaxSequence()){
            throw new IllegalArgumentException("Movie sequence is invalid. It must be between 1 and " + getMaxSequence());
        }
    }

    @Override
    public StringBuilder getFormattedShowings(ShowingFormatterService.Format format) {
        return showingFormatterService.formatShowings(showings, format);
    }

    @Override
    public StringBuilder getFormattedShowing(ShowingFormatterService.Format format, int sequence) {
        return showingFormatterService.formatShowing(showings.get(sequence), format);
    }
}
