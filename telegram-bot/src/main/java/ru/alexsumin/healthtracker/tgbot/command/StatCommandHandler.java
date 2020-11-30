package ru.alexsumin.healthtracker.tgbot.command;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.alexsumin.healthtracker.core.api.StatDTO;
import ru.alexsumin.healthtracker.tgbot.model.CommandEnum;
import ru.alexsumin.healthtracker.tgbot.model.CommandResponse;
import ru.alexsumin.healthtracker.tgbot.service.CoreService;

@Component
@RequiredArgsConstructor
public class StatCommandHandler implements CommandHandler {
    private final CoreService coreService;

    @Override
    public CommandEnum type() {
        return CommandEnum.STAT;
    }

    @Override
    public CommandResponse execute(Message message) {
        Long chatId = message.getChatId();
        StatDTO stat = coreService.stat(chatId);
        return new CommandResponse(SendMessage
                .builder()
                .chatId(String.valueOf(chatId))
                .text("Your total progress is " + stat.getTotalDifference() + " kg." )
                .build());
    }
}
