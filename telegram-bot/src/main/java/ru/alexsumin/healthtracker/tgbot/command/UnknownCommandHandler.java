package ru.alexsumin.healthtracker.tgbot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.alexsumin.healthtracker.tgbot.model.CommandEnum;
import ru.alexsumin.healthtracker.tgbot.model.CommandResponse;

@Component
public class UnknownCommandHandler implements CommandHandler {
    private static final String UNKNOWN = "Oops! I'm sorry, but I'm not smart guy, so I don't quite understand you. " +
            "I can only recognize a few commands. Send /help to see them.";

    @Override
    public CommandEnum type() {
        return CommandEnum.UNKNOWN;
    }

    @Override
    public CommandResponse execute(Message message) {
        return new CommandResponse(SendMessage
                .builder()
                .chatId(String.valueOf(message.getChatId()))
                .text(UNKNOWN)
                .build());
    }
}
