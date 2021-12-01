package ru.dbelokursky;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.dbelokursky.bot.TelegramStocksBot;

@Slf4j
@SpringBootApplication
public class StocksBootApplication implements CommandLineRunner {

    @Autowired
    private TelegramStocksBot telegramBot;

    public static void main(String[] args) {
        SpringApplication.run(StocksBootApplication.class, args);


    }

    @Override
    public void run(String... args) throws Exception {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(telegramBot);
        } catch (TelegramApiException e) {
            log.error(e.getLocalizedMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
