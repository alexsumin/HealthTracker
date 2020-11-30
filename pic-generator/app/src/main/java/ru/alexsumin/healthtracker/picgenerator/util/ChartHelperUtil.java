package ru.alexsumin.healthtracker.picgenerator.util;

import lombok.experimental.UtilityClass;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

@UtilityClass
public class ChartHelperUtil {

    public double findMax(@NotEmpty List<BigDecimal> list) {
        double value = list.stream()
                .max(BigDecimal::compareTo)
                .get()
                .doubleValue();
        return Math.ceil(value) + 1;
    }

    public double findMin(@NotEmpty List<BigDecimal> list) {
        double value = list.stream()
                .min(BigDecimal::compareTo)
                .get()
                .doubleValue();
        return Math.floor(value) - 1;
    }

    public double calculateTick(double min, double max) {
        double value = (max - min);
        if (value <= 3) return 0.5;
        if (value <= 7) return 1;
        return Math.floor(max - min) / 10;
    }


}
