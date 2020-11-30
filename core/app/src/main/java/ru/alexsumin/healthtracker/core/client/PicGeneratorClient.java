package ru.alexsumin.healthtracker.core.client;

import org.springframework.cloud.openfeign.FeignClient;
import ru.alexsumin.healthtracker.picgenerator.api.PicGeneratorApi;

@FeignClient(name = "pic-generator")
public interface PicGeneratorClient extends PicGeneratorApi {
}
