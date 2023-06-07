package com.jpmc.theater.services;

import com.jpmc.theater.Showing;

import java.util.List;

public interface ShowingFormatterService {

    enum Format {
        TEXT,
        JSON
    }

    StringBuilder formatShowings(List<Showing> showings, Format format);

    StringBuilder formatShowing(Showing showings, Format format);
}
