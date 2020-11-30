package ru.alexsumin.healthtracker.tgbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alexsumin.healthtracker.core.api.*;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CoreService {

    private final CoreApi coreApi;

    public BigDecimal addNewMeasurement(Long chatId, BigDecimal value) {
        var dto = MeasurementDTO.builder()
                .value(value)
                .type(MeasurementType.WEIGHT)
                .build();
        return coreApi.addNewMeasurement(dto, chatId).getValue();
    }

    public void createUser(Long chatId) {
        coreApi.createUser(UserDTO.builder().id(chatId).build());
    }

    public byte[] chart(Long chatId)  {
        return coreApi.getChart(chatId);
    }

    public void removeLast(Long chatId) {
        coreApi.removeLastMeasurement(chatId, MeasurementType.WEIGHT);
    }

    public StatDTO stat(Long chatId) {
        return coreApi.stat(chatId, MeasurementType.WEIGHT);
    }
}
