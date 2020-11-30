package ru.alexsumin.healthtracker.core.util;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class ProjectUtil {

    public BigDecimal calcDifference(BigDecimal prev, BigDecimal current) {
        return prev.subtract(current);
    }
}
