package ru.dbelokursky.mq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.dbelokursky.bot.TelegramStocksBot;
import ru.dbelokursky.dto.StocksMessage;
import ru.dbelokursky.formatters.StockInfoFormatter;


@Slf4j
@RequiredArgsConstructor
@Component
public class Receiver {

    private final TelegramStocksBot telegramBot;

    public void receiveMessage(StocksMessage message) {
        if (message.getChatId() != null) {
            telegramBot.sendMessage(message.getChatId(), StockInfoFormatter.format(message.getStocks()));
            log.info("Received <{}>", message);
        }
    }

}
