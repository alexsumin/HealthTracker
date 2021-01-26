package ru.alexsumin.healthtracker.tgbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alexsumin.healthtracker.core.api.*;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CoreService {

    private final CoreApi coreApi;

    public DifferenceDTO addNewMeasurement(Long chatId, BigDecimal value) {
        var dto = MeasurementDTO.builder()
                .value(value)
                .build();
        return coreApi.addNewMeasurement(dto, chatId);
    }

    public void createUser(Long chatId) {
        coreApi.createUser(UserDTO.builder().id(chatId).build());
    }

    public byte[] chart(Long chatId)  {
        return coreApi.getChart(chatId);
    }

    public void removeLast(Long chatId) {
        coreApi.removeLastMeasurement(chatId);
    }

    public StatDTO stat(Long chatId) {
        return coreApi.stat(chatId);
    }
}
