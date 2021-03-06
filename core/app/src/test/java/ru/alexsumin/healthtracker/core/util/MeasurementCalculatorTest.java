package ru.alexsumin.healthtracker.core.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MeasurementCalculatorTest {

    @Test
    public void calcDifferenceTest() {
        var calculator = new MeasurementCalculator();

        var first = new BigDecimal("120");
        var second = new BigDecimal("50");
        var expectedResult = new BigDecimal("-70");

        var result = calculator.calcDifference(first, second);
        assertEquals(expectedResult, result);
    }

}