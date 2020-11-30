package ru.alexsumin.healthtracker.core.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.alexsumin.healthtracker.core.api.MeasurementDTO;
import ru.alexsumin.healthtracker.core.api.MeasurementType;
import ru.alexsumin.healthtracker.core.api.StatDTO;
import ru.alexsumin.healthtracker.core.api.UserDTO;
import ru.alexsumin.healthtracker.core.mapper.MeasurementMapper;
import ru.alexsumin.healthtracker.core.mapper.UserMapper;
import ru.alexsumin.healthtracker.core.service.MainService;
import ru.alexsumin.healthtracker.core.service.MeasurementService;
import ru.alexsumin.healthtracker.core.service.PicGeneratorService;
import ru.alexsumin.healthtracker.core.service.UserService;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MainServiceImpl implements MainService {

    MeasurementService measurementService;
    UserService userService;
    MeasurementMapper measurementMapper;
    UserMapper userMapper;
    PicGeneratorService picGeneratorService;

    @Override
    public BigDecimal addNewMeasurement(MeasurementDTO newMeasurement, Long id) {
        return measurementService.addNewAndReturnDifference(
                measurementMapper.toMeasurement(newMeasurement), id);
    }

    @Override
    public void createUser(UserDTO userDTO) {
        userService.create(userMapper.toUser(userDTO));
    }

    @Override
    public byte[] createChart(Long userId) {
        return picGeneratorService.createChart(userId);
    }

    @Override
    public void removeLastMeasurement(Long userID, MeasurementType type) {
        measurementService.removeUsersLastMeasurement(userID, type);
    }

    @Override
    public StatDTO stat(Long userId, MeasurementType type) {
        return StatDTO.builder()
                .totalDifference(measurementService.stat(userId, type))
                .build();
    }
}
