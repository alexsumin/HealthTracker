package ru.alexsumin.healthtracker.core.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

public interface CoreApi {

    @PostMapping(path = "v1/user/{id}/measurement/")
    DifferenceDTO addNewMeasurement(MeasurementDTO measurementDTO, @PathVariable("id") Long id);

    @PostMapping(path = "v1/user")
    void createUser(UserDTO userDTO);

    @GetMapping(path = "v1/user/{id}/chart/", produces = MediaType.IMAGE_PNG_VALUE)
    byte[] getChart(@PathVariable("id") Long userId);

    @PostMapping(path = "v1/user/{id}/measurement/removeLast")
    void removeLastMeasurement(@PathVariable("id") Long userId);

    @GetMapping(path = "v1/user/{id}/stat/")
    StatDTO stat(@PathVariable("id") Long userId);
}
