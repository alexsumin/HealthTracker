package ru.alexsumin.healthtracker.core.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProjectUtilTest {

    @Test
    public void calcDifferenceTest() {
        var first = new BigDecimal("120");
        var second = new BigDecimal("50");
        var expectedResult = new BigDecimal("-70");

        var result = ProjectUtil.calcDifference(first, second);
        assertEquals(expectedResult, result);
    }

}