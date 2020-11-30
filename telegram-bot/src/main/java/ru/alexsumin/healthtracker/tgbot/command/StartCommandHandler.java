package ru.alexsumin.healthtracker.tgbot.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.alexsumin.healthtracker.tgbot.model.CommandEnum;
import ru.alexsumin.healthtracker.tgbot.model.CommandResponse;
import ru.alexsumin.healthtracker.tgbot.service.CoreService;

@Component
@RequiredArgsConstructor
public class StartCommandHandler implements CommandHandler {
    private final CoreService coreService;

    private static final String START = "What is this bot designed for? " +
            "This simple bot helps you to monitor your weight, presenting the results as a chart. " +
            "To receive the clue just write /help. " +
            "New functions will be added soon! Have a nice day :)";

    @Override
    public CommandEnum type() {
        return CommandEnum.START;
    }

    @Override
    public CommandResponse execute(Message message) {
        coreService.createUser(message.getChatId());
        return new CommandResponse(SendMessage
                .builder()
                .chatId(String.valueOf(message.getChatId()))
                .text(START)
                .build());
    }
}
