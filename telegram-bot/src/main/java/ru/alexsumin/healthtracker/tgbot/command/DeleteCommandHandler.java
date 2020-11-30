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
public class DeleteCommandHandler implements CommandHandler {
    private static final String SUCCESS = "The last measurement was successfully deleted!";
    private static final String NO_VALUES = "Oops! No measurements were found. Just add the new one to start!";

    private final CoreService service;

    @Override
    public CommandEnum type() {
        return CommandEnum.DELETE;
    }

    @Override
    public CommandResponse execute(Message message) {
        Long id = message.getChatId();
        service.removeLast(id);
        //todo: no values case
        return new CommandResponse(SendMessage
                .builder()
                .chatId(String.valueOf(id))
                .text(SUCCESS)
                .build());
    }
}
