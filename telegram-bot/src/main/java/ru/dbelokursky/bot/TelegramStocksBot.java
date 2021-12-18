package ru.dbelokursky.bot;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.dbelokursky.dto.BotMessage;
import ru.dbelokursky.dto.StockDto;
import ru.dbelokursky.enums.TickerEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.dbelokursky.config.RabbitmqConfig.REQUEST_EXCHANGE_NAME;
import static ru.dbelokursky.config.RabbitmqConfig.REQUEST_ROUTING_KEY;

@Component
@Slf4j
@RequiredArgsConstructor
public class TelegramStocksBot extends TelegramLongPollingBot {

    private static final String GET_STOCK_PRICE_MESSAGE = """
            Для получения информации о бумаге 
            введите название тикера (например IDCC)""";


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
        if (update.hasCallbackQuery()) {
            handleCallback(update.getCallbackQuery());
        } else if (update.hasMessage()) {
            handleMessage(update.getMessage());
        }
    }

    @SneakyThrows
    private void handleCallback(CallbackQuery callbackQuery) {
        Message message = callbackQuery.getMessage();
        String data = callbackQuery.getData();
        BotMessage botMessage = BotMessage.builder()
                .chatId(message.getChatId().toString())
                .stockDto(StockDto.builder().ticker(data).build())
                .build();

        template.convertAndSend(REQUEST_EXCHANGE_NAME, REQUEST_ROUTING_KEY, botMessage);


    }

    @SneakyThrows
    private void handleMessage(Message message) {
        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity = message.getEntities().stream()
                    .filter(c -> "bot_command".equals(c.getType()))
                    .findAny();

            if (commandEntity.isPresent()) {
                String command = message.getText().substring(commandEntity.get().getOffset(), commandEntity.get().getLength());

                switch (command) {
                    case "/get_stock_price" -> {
                        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

                        for (TickerEnum tickerEnum : TickerEnum.values()) {
                            buttons.add(List.of(
                                    InlineKeyboardButton.builder()
                                            .text(String.format("%s (%s)", tickerEnum.description, tickerEnum.ticker))
                                            .callbackData(tickerEnum.ticker)
                                            .build()
                            ));
                        }

                        String chatId = message.getChatId().toString();
                        execute(SendMessage.builder()
                                .chatId(chatId)
                                .text(GET_STOCK_PRICE_MESSAGE)
                                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build())
                                .build());

                    }

                }
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
