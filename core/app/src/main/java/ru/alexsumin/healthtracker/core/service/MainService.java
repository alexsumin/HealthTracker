package ru.alexsumin.healthtracker.core.service;


import ru.alexsumin.healthtracker.core.api.MeasurementDTO;
import ru.alexsumin.healthtracker.core.api.MeasurementType;
import ru.alexsumin.healthtracker.core.api.StatDTO;
import ru.alexsumin.healthtracker.core.api.UserDTO;

import java.math.BigDecimal;

public interface MainService {
    BigDecimal addNewMeasurement(MeasurementDTO newMeasurement, Long id);
    void createUser(UserDTO userDTO);
    byte[] createChart(Long userId);
    void removeLastMeasurement(Long userID, MeasurementType type);
    StatDTO stat(Long userId, MeasurementType type);
}
