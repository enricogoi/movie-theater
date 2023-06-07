package com.jpmc.theater.services;

import com.jpmc.theater.Movie;
import com.jpmc.theater.Showing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowingFormatterServiceTest {

    private final String EXPECTED_TEXT = "===================================================\n" +
            "1: 2023-06-05T11:10, Spider-Man: No Way Home, 1 hour 30 minutes, $11.0\n" +
            "2: 2023-06-05T15:14, Turning Red, 1 hour 25 minutes, $14.32\n" +
            "===================================================";
    private final String SINGLE_EXPECTED_TEST = "1: 2023-06-05T11:10, Spider-Man: No Way Home, 1 hour 30 minutes, $11.0\n";

    private final String EXPECTED_JSON_FORMAT = "===================================================\n" +
            "{\n" +
            "  \"movie\": {\n" +
            "    \"title\": \"Spider-Man: No Way Home\",\n" +
            "    \"runningTime\": \"1 hour 30 minutes\",\n" +
            "    \"baseTicketPrice\": 12.50,\n" +
            "    \"specialCode\": 1\n" +
            "  },\n" +
            "  \"sequenceOfTheDay\": 1,\n" +
            "  \"showStartTime\": \"5::Jun::2023 11::10::00\",\n" +
            "  \"effectiveMoviePrice\": 11.0\n" +
            "}\n" +
            "{\n" +
            "  \"movie\": {\n" +
            "    \"title\": \"Turning Red\",\n" +
            "    \"runningTime\": \"1 hour 25 minutes\",\n" +
            "    \"baseTicketPrice\": 11.00,\n" +
            "    \"specialCode\": 0\n" +
            "  },\n" +
            "  \"sequenceOfTheDay\": 2,\n" +
            "  \"showStartTime\": \"5::Jun::2023 15::14::00\",\n" +
            "  \"effectiveMoviePrice\": 14.32\n" +
            "}\n" +
            "===================================================";


    private ShowingFormatterService showingFormatterService = new ShowingFormatterServiceImpl();
    private List<Showing> showingList;

    @BeforeEach
    public void initShowingList(){
        showingList = new ArrayList<>();
        Movie spiderMan =  new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), BigDecimal.valueOf(12.5), 1);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(2023, 6, 5, 11, 10));
        showing.setEffectiveMoviePrice(BigDecimal.valueOf(11.0));
        showingList.add(showing);

        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), BigDecimal.valueOf(11), 0);
        Showing showing2 = new Showing(turningRed, 2, LocalDateTime.of(2023, 6, 5, 15, 14));
        showing2.setEffectiveMoviePrice(BigDecimal.valueOf(14.32));
        showingList.add(showing2);
    }

    @Test
    public void textFormatShowings(){
        assertEquals(EXPECTED_TEXT, showingFormatterService.formatShowings(showingList, ShowingFormatterService.Format.TEXT).toString());
    }

    @Test
    public void textFormatShowing(){
        assertEquals(SINGLE_EXPECTED_TEST, showingFormatterService.formatShowing(showingList.get(0), ShowingFormatterService.Format.TEXT).toString());
    }

    @Test
    public void jsonFormatShowings(){
        assertEquals(EXPECTED_JSON_FORMAT, showingFormatterService.formatShowings(showingList, ShowingFormatterService.Format.JSON).toString());

    }
}
