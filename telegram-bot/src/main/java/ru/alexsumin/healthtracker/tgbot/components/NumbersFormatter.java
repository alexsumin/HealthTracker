package ru.alexsumin.healthtracker.tgbot.components;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class NumbersFormatter {

    public String getDifferenceWithSign(BigDecimal value) {
        if (value.doubleValue() < 0) {
            return "Last change: " + value + " kg.";
        }
        return "Last change: +" + value + " kg.";
    }
}
