package ru.dbelokursky.bot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.dbelokursky.dto.Message;

import static ru.dbelokursky.config.RabbitmqConfig.REQUEST_EXCHANGE_NAME;
import static ru.dbelokursky.config.RabbitmqConfig.REQUEST_ROUTING_KEY;

@Component
@Slf4j
@RequiredArgsConstructor
public class TelegramStocksBot extends TelegramLongPollingBot {

    @Value("${app.telegram.token}")
    private String telegramBotToken;

    @Value("${app.telegram.name}")
    private String telegramBotName;

    private final RabbitTemplate template;


    @Override
    public String getBotUsername() {
        return telegramBotName;
    }

    @Override
    public String getBotToken() {
        return telegramBotToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();

            SendMessage telegramMessage = new SendMessage();
            telegramMessage.setChatId(chatId);

            Message message = Message.builder().chatId(chatId).build();

            template.convertAndSend(REQUEST_EXCHANGE_NAME, REQUEST_ROUTING_KEY, message);

            try {
                execute(telegramMessage);
            } catch (TelegramApiException e) {
                log.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }
    }

    public void sendMessage(String chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
