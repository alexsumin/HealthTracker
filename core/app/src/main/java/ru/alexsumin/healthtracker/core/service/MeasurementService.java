package ru.alexsumin.healthtracker.core.service;

import ru.alexsumin.healthtracker.core.api.MeasurementType;
import ru.alexsumin.healthtracker.core.domain.entity.Measurement;

import java.math.BigDecimal;

public interface MeasurementService {
    BigDecimal addNewAndReturnDifference(Measurement measurement, Long id);
    Measurement getUsersLastMeasurement(Long accountId);
    void removeUsersLastMeasurement(Long accountId, MeasurementType type);
    BigDecimal stat(Long accountId, MeasurementType type);
}
