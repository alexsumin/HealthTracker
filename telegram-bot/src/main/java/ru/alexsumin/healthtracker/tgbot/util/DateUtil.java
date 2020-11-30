package ru.alexsumin.healthtracker.tgbot.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateUtil {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM");

    public String getCurrentDate() {
        return formatter.format(LocalDate.now());
    }
}
