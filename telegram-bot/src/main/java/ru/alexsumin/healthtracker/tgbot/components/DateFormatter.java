package ru.alexsumin.healthtracker.tgbot.components;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DateFormatter {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM");

    public String getCurrentDate() {
        return formatter.format(LocalDate.now());
    }
}
