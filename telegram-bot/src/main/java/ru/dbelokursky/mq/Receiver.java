package ru.dbelokursky.mq;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.dbelokursky.bot.TelegramStocksBot;
import ru.dbelokursky.dto.Message;

@RequiredArgsConstructor
@Component
public class Receiver {

    private final TelegramStocksBot telegramBot;

    public void receiveMessage(Message message) {
        telegramBot.sendMessage(message.getChatId(), message.getStockDto().toString());
        System.out.println("Received <" + message + ">");
    }

}
