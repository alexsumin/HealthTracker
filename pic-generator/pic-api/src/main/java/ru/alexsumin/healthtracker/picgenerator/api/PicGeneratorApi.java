package ru.alexsumin.healthtracker.picgenerator.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

public interface PicGeneratorApi {

    @RequestMapping(value = "/v1/chart/generate", method = POST, produces = MediaType.IMAGE_PNG_VALUE)
    byte[] generateChart(@RequestBody @Valid ChartRequest request);
}
