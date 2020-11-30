package ru.alexsumin.healthtracker.core.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CachedState {
    Long userId;
    BigDecimal actualValue;
}
