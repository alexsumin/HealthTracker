package ru.alexsumin.healthtracker.tgbot.components;

import org.springframework.stereotype.Component;
import ru.alexsumin.healthtracker.tgbot.model.CommandEnum;

import java.util.Map;

@Component
public class CommandResolver {

    private static Map<String, CommandEnum> map = Map.of(
            "/start", CommandEnum.START,
            "/help", CommandEnum.HELP,
            "/chart", CommandEnum.CHART,
            "/stat", CommandEnum.STAT,
            "/delete", CommandEnum.DELETE
    );

    public CommandEnum detectCommandType(String text) {
        CommandEnum command = map.get(text.toLowerCase());
        if (command != null) return command;
        if (!isWithDigits(text)) return CommandEnum.UNKNOWN;
        return CommandEnum.ADD_VALUE;
    }

    private boolean isWithDigits(String text) {
        try {
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
