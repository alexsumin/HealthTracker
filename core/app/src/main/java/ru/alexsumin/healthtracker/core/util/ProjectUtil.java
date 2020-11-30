package ru.alexsumin.healthtracker.core.util;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.MathContext;

@UtilityClass
public class ProjectUtil {

    public BigDecimal calcDifference(BigDecimal prev, BigDecimal current) {
        MathContext mc = new MathContext(2);
        return current.subtract(prev, mc);
    }
}
