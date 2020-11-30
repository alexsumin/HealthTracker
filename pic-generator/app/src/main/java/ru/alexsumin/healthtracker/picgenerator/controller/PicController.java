package ru.alexsumin.healthtracker.picgenerator.controller;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alexsumin.healthtracker.picgenerator.api.ChartRequest;
import ru.alexsumin.healthtracker.picgenerator.api.PicGeneratorApi;
import ru.alexsumin.healthtracker.picgenerator.fx.ChartGenerator;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@Validated
public class PicController implements PicGeneratorApi {

    @Override
    @RequestMapping(value = "/v1/chart/generate", method = POST, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] generateChart(@RequestBody @Valid ChartRequest request) {
        return ChartGenerator.generateChart(request.getValues(), request.getTitle());
    }
}
