package ru.alexsumin.healthtracker.tgbot.command;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.alexsumin.healthtracker.tgbot.model.CommandEnum;
import ru.alexsumin.healthtracker.tgbot.model.CommandResponse;

public interface CommandHandler {
    CommandEnum type();
    CommandResponse execute(Message message);
}
