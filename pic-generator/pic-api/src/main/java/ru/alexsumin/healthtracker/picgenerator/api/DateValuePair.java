package ru.alexsumin.healthtracker.picgenerator.api;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DateValuePair {
    LocalDate date;
    BigDecimal value;
}
