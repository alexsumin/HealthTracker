package ru.alexsumin.healthtracker.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.alexsumin.healthtracker.core.api.*;
import ru.alexsumin.healthtracker.core.service.MainService;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class CoreController implements CoreApi {
    private final MainService service;

    @Override
    @PostMapping(path = "v1/user/{id}/measurement")
    public DifferenceDTO addNewMeasurement(@RequestBody MeasurementDTO measurementDTO, @PathVariable("id") Long id) {
         return service.addNewMeasurement(measurementDTO, id);
    }

    @Override
    @PostMapping(path = "v1/user")
    public void createUser(@RequestBody UserDTO userDTO) {
        service.createUser(userDTO);
    }

    @Override
    @GetMapping(path = "v1/user/{id}/chart", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getChart(@PathVariable("id") Long userId) {
        return service.createChart(userId);
    }

    @Override
    @PostMapping(path = "v1/user/{id}/measurement/{type}/removeLast")
    public void removeLastMeasurement(@PathVariable("id") Long userId, @PathVariable("type") MeasurementType type) {
        service.removeLastMeasurement(userId, type);
    }

    @Override
    @GetMapping(path = "v1/user/{id}/stat/{type}/")
    public StatDTO stat(@PathVariable("id") Long userId, @PathVariable("type") MeasurementType type) {
        return service.stat(userId, type);
    }
}
