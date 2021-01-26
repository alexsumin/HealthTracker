package ru.alexsumin.healthtracker.core.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.alexsumin.healthtracker.core.api.*;
import ru.alexsumin.healthtracker.core.mapper.MeasurementMapper;
import ru.alexsumin.healthtracker.core.mapper.UserMapper;
import ru.alexsumin.healthtracker.core.service.MainService;
import ru.alexsumin.healthtracker.core.service.MeasurementService;
import ru.alexsumin.healthtracker.core.service.PicGeneratorService;
import ru.alexsumin.healthtracker.core.service.UserService;

import java.math.BigDecimal;
import java.util.Optional;

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
    public DifferenceDTO addNewMeasurement(MeasurementDTO newMeasurement, Long id) {
        Optional<BigDecimal> optionalValue = measurementService.addNewAndReturnDifference(
                measurementMapper.toMeasurement(newMeasurement), id);
        if (optionalValue.isPresent()) {
            return DifferenceDTO.builder().value(optionalValue.get()).isFirst(false).build();
        }
        return DifferenceDTO.builder().isFirst(true).build();
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
    public void removeLastMeasurement(Long userID) {
        measurementService.removeUsersLastMeasurement(userID);
    }

    @Override
    public StatDTO stat(Long userId) {
        return StatDTO.builder()
                .totalDifference(measurementService.stat(userId))
                .build();
    }
}
