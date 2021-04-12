package edu.epam.bookie.tag;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateFormatTag {
    private static final String DATE_PATTERN =  "dd/MM/yyyy, HH:mm";

    private DateFormatTag() {}

    public static String formatDateAndTime(LocalDate date, LocalTime time) {
        LocalDateTime dateTime  = LocalDateTime.of(date, time);
        return dateTime.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }
}
