package ru.alexsumin.healthtracker.tgbot.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.alexsumin.healthtracker.tgbot.model.CommandEnum;
import ru.alexsumin.healthtracker.tgbot.model.CommandResponse;
import ru.alexsumin.healthtracker.tgbot.service.CoreService;

import java.io.ByteArrayInputStream;

@Component
@RequiredArgsConstructor
public class ChartCommandHandler implements CommandHandler {
    private final CoreService coreService;

    @Override
    public CommandEnum type() {
        return CommandEnum.CHART;
    }

    @Override
    public CommandResponse execute(Message message) {
        byte[] chart = coreService.chart(message.getChatId());
        return new CommandResponse(
                SendPhoto.builder()
                        .chatId(String.valueOf(message.getChatId()))
                        .photo(new InputFile(new ByteArrayInputStream(chart), "chart.png"))
                        .build()
        );

    }
}
