package ru.alexsumin.healthtracker.tgbot.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.alexsumin.healthtracker.tgbot.model.CommandEnum;
import ru.alexsumin.healthtracker.tgbot.model.CommandResponse;
import ru.alexsumin.healthtracker.tgbot.service.CoreService;
import ru.alexsumin.healthtracker.tgbot.util.DateUtil;
import ru.alexsumin.healthtracker.tgbot.util.NumbersUtil;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class AddValueCommandHandler implements CommandHandler {
    private final CoreService coreService;

    @Override
    public CommandEnum type() {
        return CommandEnum.ADD_VALUE;
    }

    @Override
    public CommandResponse execute(Message message) {
        Long id = message.getChatId();
        String text = message.getText();
        var value = NumberUtils.parseNumber(text, BigDecimal.class);
        BigDecimal response = coreService.addNewMeasurement(id, value);

        var msgText = new StringBuilder()
                .append("Well, The new measurement ")
                .append(text)
                .append(" kg was added on ")
                .append(DateUtil.getCurrentDate())
                .append(".\n")
                .append(NumbersUtil.getDifferenceWithSign(value, response));

        return new CommandResponse(SendMessage
                .builder()
                .chatId(String.valueOf(id))
                .text(msgText.toString())
                .build());
    }
}
