package ru.alexsumin.healthtracker.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alexsumin.healthtracker.core.client.PicGeneratorClient;
import ru.alexsumin.healthtracker.core.domain.entity.Measurement;
import ru.alexsumin.healthtracker.core.repository.MeasurementRepository;
import ru.alexsumin.healthtracker.core.service.PicGeneratorService;
import ru.alexsumin.healthtracker.picgenerator.api.ChartRequest;
import ru.alexsumin.healthtracker.picgenerator.api.DateValuePair;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PicGeneratorServiceImpl implements PicGeneratorService {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM");

    private final MeasurementRepository measurementRepository;
    private final PicGeneratorClient client;

    public byte[] createChart(Long id) {

        var values = measurementRepository.findAllByUser_Id(id)
                .stream()
                .map(measurement -> DateValuePair.builder().value(measurement.getData()).date(measurement.getCreationDate().toLocalDate()).build())
                .collect(Collectors.toList());

        var request = new ChartRequest();
        request.setTitle("Your weight stat on " + formatter.format(LocalDate.now()));
        request.setValues(values);

        return client.generateChart(request);
    }
}
