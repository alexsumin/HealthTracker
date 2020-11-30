package ru.alexsumin.healthtracker.core.service;


import ru.alexsumin.healthtracker.core.api.*;

public interface MainService {
    DifferenceDTO addNewMeasurement(MeasurementDTO newMeasurement, Long id);
    void createUser(UserDTO userDTO);
    byte[] createChart(Long userId);
    void removeLastMeasurement(Long userID, MeasurementType type);
    StatDTO stat(Long userId, MeasurementType type);
}
