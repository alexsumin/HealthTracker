package ru.alexsumin.healthtracker.tgbot.util;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class NumbersUtil {

    public String getDifferenceWithSign(BigDecimal value) {
        if (value.doubleValue() < 0)
            return "Last change: " + value + " kg.";
        else return "Last change: +" + value + " kg.";
    }
}
