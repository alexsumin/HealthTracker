package ru.alexsumin.healthtracker.tgbot.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.alexsumin.healthtracker.tgbot.command.CommandHandler;
import ru.alexsumin.healthtracker.tgbot.components.CommandResolver;
import ru.alexsumin.healthtracker.tgbot.model.CommandEnum;
import ru.alexsumin.healthtracker.tgbot.model.CommandResponse;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommandHandlerManager {

    private final Set<CommandHandler> handlersSet;
    private final CommandResolver resolver;

    private Map<CommandEnum, CommandHandler> handlersMap = new EnumMap<>(CommandEnum.class);

    @PostConstruct
    public void init() {
        handlersSet.forEach(command -> handlersMap.put(command.type(), command));
    }

    @HystrixCommand(commandKey = "execute", fallbackMethod = "defaultFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000"),
            @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")})
    public CommandResponse handleMessage(Message message) {
        var commandType = resolver.detectCommandType(message.getText());
        var command = handlersMap.get(commandType);
        return command.execute(message);
    }

    @SuppressWarnings("unused")
    public CommandResponse defaultFallback(Message message, Throwable throwable) {
        log.error(throwable.getMessage(), throwable);
        return new CommandResponse(SendMessage
                .builder()
                .chatId(String.valueOf(message.getChatId()))
                .text("Ooups! :( Something went wrong!\nPlease, try again later")
                .build());
    }
}
