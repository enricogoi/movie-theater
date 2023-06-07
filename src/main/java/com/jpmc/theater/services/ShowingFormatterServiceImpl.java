package com.jpmc.theater.services;

import com.google.gson.*;
import com.jpmc.theater.Showing;

import java.lang.reflect.Type;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShowingFormatterServiceImpl implements ShowingFormatterService {

    private final Gson gson;

    public ShowingFormatterServiceImpl(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Duration.class, new DurationSerializer());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        this.gson = gsonBuilder.setPrettyPrinting().create();
    }

    @Override
    public StringBuilder formatShowings(List<Showing> showings, Format format) {
        final StringBuilder sb = new StringBuilder();
        sb.append("===================================================\n");
        showings.forEach(s -> formatShowing(s, format, sb));
        sb.append("===================================================");
        return sb;
    }

    @Override
    public StringBuilder formatShowing(Showing showing, Format format) {
        final StringBuilder sb = new StringBuilder();
        formatShowing(showing, format, sb);
        return sb;
    }

    private void formatShowing(Showing showing, Format format, StringBuilder sb) {
        switch (format){
            case TEXT:
                textFormat(showing, sb);
                break;
            case JSON:
                jsonFormat(showing, sb);
                break;
        }
    }

    private void textFormat(Showing showing, StringBuilder sb){
        sb.append(showing.getSequenceOfTheDay()).append(": ")
          .append(showing.getStartTime()).append(", ")
          .append(showing.getMovie().getTitle()).append(", ");
        humanReadableFormat(showing.getMovie().getRunningTime(), sb);
        sb.append(", $").append(showing.getEffectiveMoviePrice()).append("\n");
    }

    private void humanReadableFormat(Duration duration, StringBuilder sb) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());
        sb.append(hour).append(" hour").append(hour == 1  ? " " : "s ").append(remainingMin).append(" minute").append(remainingMin == 1  ? "" : "s");
    }

    private String humanReadableFormat(Duration duration) {
        StringBuilder sb = new StringBuilder();
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());
        return sb.append(hour).append(" hour").append(hour == 1  ? " " : "s ").append(remainingMin).append(" minute").append(remainingMin == 1  ? "" : "s").toString();
    }

    private void jsonFormat(Showing showing, StringBuilder sb){
        sb.append(gson.toJson(showing)).append("\n");
    }

    class DurationSerializer implements JsonSerializer<Duration> {
        @Override
        public JsonElement serialize(Duration duration, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(humanReadableFormat(duration));
        }
    }

    class LocalDateTimeSerializer implements JsonSerializer <LocalDateTime> {
        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss");

        @Override
        public JsonElement serialize(LocalDateTime localDateTime, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(localDateTime));
        }
    }
}
