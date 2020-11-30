package ru.alexsumin.healthtracker.tgbot.client;

import org.springframework.cloud.openfeign.FeignClient;
import ru.alexsumin.healthtracker.core.api.CoreApi;

@FeignClient(name = "core-api")
public interface CoreApiClient extends CoreApi {
}
