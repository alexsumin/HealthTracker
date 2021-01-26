package ru.alexsumin.healthtracker.core.service;

import ru.alexsumin.healthtracker.core.domain.entity.Measurement;

import java.math.BigDecimal;
import java.util.Optional;

public interface MeasurementService {
    Optional<BigDecimal> addNewAndReturnDifference(Measurement measurement, Long id);
    Measurement getUsersLastMeasurement(Long accountId);
    void removeUsersLastMeasurement(Long accountId);
    BigDecimal stat(Long accountId);
}
