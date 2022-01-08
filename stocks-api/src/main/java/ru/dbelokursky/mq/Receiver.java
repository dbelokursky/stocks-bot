package ru.dbelokursky.mq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import ru.dbelokursky.dto.StockRequestMessage;
import ru.dbelokursky.dto.StocksMessage;
import ru.dbelokursky.service.StockService;

import static ru.dbelokursky.config.RabbitmqConfig.RESPONSE_EXCHANGE_NAME;
import static ru.dbelokursky.config.RabbitmqConfig.RESPONSE_ROUTING_KEY;
import static ru.dbelokursky.enums.CommandEnum.GET_STOCK_INFO;
import static ru.dbelokursky.enums.CommandEnum.GET_STOCK_PRICE;

@Slf4j
@RequiredArgsConstructor
@Component
public class Receiver {

    private final StockService service;
    private final RabbitTemplate rabbitTemplate;

    public void receiveMessage(StockRequestMessage message) {
        StocksMessage responseMessage = StocksMessage.builder().build();

        //TODO возможно нужно сделать разные мессаджи для инфо и для прайса
        if (GET_STOCK_PRICE.getName().equals(message.getCommand())) {
            responseMessage = StocksMessage.builder()
                    .chatId(message.getChatId())
                    .stocks(service.getStockInfoByTicker(message.getTicker()))
                    .build();
        } else if (GET_STOCK_INFO.getName().equals(message.getCommand())) {
            responseMessage = StocksMessage.builder()
                    .chatId(message.getChatId())
                    .stocks(service.getStockInfoByTicker(message.getTicker()))
                    .build();
        }

        rabbitTemplate.convertAndSend(RESPONSE_EXCHANGE_NAME, RESPONSE_ROUTING_KEY, responseMessage);
        log.info("Received <{}>", message);
    }

}
