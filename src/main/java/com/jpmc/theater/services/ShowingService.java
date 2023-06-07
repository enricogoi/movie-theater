package com.jpmc.theater.services;

import com.jpmc.theater.Movie;
import com.jpmc.theater.Showing;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowingService {

    List<Showing> getShowings();
    void addShowing(Movie movie, LocalDateTime startTime);

    int getMaxSequence();

    void checkSequence(int sequence) throws IllegalArgumentException;

    StringBuilder getFormattedShowings(ShowingFormatterService.Format format);

    StringBuilder getFormattedShowing(ShowingFormatterService.Format format, int sequence);

}
