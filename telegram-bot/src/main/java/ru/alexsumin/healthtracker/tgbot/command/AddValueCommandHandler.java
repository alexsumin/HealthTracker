package ru.alexsumin.healthtracker.tgbot.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.alexsumin.healthtracker.core.api.DifferenceDTO;
import ru.alexsumin.healthtracker.tgbot.model.CommandEnum;
import ru.alexsumin.healthtracker.tgbot.model.CommandResponse;
import ru.alexsumin.healthtracker.tgbot.service.CoreService;
import ru.alexsumin.healthtracker.tgbot.components.DateFormatter;
import ru.alexsumin.healthtracker.tgbot.components.NumbersFormatter;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class AddValueCommandHandler implements CommandHandler {
    private final CoreService coreService;
    private final DateFormatter dateFormatter;
    private final NumbersFormatter numbersFormatter;

    @Override
    public CommandEnum type() {
        return CommandEnum.ADD_VALUE;
    }

    @Override
    public CommandResponse execute(Message message) {
        Long id = message.getChatId();
        String text = message.getText();
        var value = NumberUtils.parseNumber(text, BigDecimal.class);
        DifferenceDTO response = coreService.addNewMeasurement(id, value);

        var msgText = new StringBuilder()
                .append("Well, The new measurement ")
                .append(text)
                .append(" kg was added on ")
                .append(dateFormatter.getCurrentDate())
                .append(".\n");
        if (!response.getIsFirst()) {
            msgText.append(numbersFormatter.getDifferenceWithSign(response.getValue()));
        }

        return new CommandResponse(SendMessage
                .builder()
                .chatId(String.valueOf(id))
                .text(msgText.toString())
                .build());
    }
}
