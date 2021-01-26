package ru.alexsumin.healthtracker.core.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;

@Component
public class MeasurementCalculator {
    private final MathContext mc = new MathContext(2);

    public BigDecimal calcDifference(BigDecimal prev, BigDecimal current) {
        return current.subtract(prev, mc);
    }
}
