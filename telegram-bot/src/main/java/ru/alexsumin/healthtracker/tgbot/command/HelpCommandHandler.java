package ru.alexsumin.healthtracker.tgbot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.alexsumin.healthtracker.tgbot.model.CommandEnum;
import ru.alexsumin.healthtracker.tgbot.model.CommandResponse;

@Component
public class HelpCommandHandler implements CommandHandler {
    private static final String HELP = "Let me explain to you how I work.\n" +
            "I keep your weight statistics.\n" +
            "To add a new measurement, simply send me this value.\n" +
            "To view a chart, send me /chart.\n" +
            "To get statistics, send me /stat.\n" +
            "To delete the last data value, send me /delete.";

    @Override
    public CommandEnum type() {
        return CommandEnum.HELP;
    }

    @Override
    public CommandResponse execute(Message message) {
        return new CommandResponse(SendMessage
                .builder()
                .chatId(String.valueOf(message.getChatId()))
                .text(HELP)
                .build());
    }
}
