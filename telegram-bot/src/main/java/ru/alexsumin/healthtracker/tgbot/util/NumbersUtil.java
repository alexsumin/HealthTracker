package ru.alexsumin.healthtracker.tgbot.util;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class NumbersUtil {

    public String getDifferenceWithSign(BigDecimal firstValue, BigDecimal secondValue) {
        BigDecimal result = secondValue.subtract(firstValue);
        if (result.doubleValue() < 0)
            return "Last change: " + result + " kg.";
        else return "Last change: +" + result + " kg.";
    }
}
