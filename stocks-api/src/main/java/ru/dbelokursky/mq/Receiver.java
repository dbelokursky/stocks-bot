package ru.dbelokursky.mq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import ru.dbelokursky.dto.Message;
import ru.dbelokursky.service.StockService;

import static ru.dbelokursky.config.RabbitmqConfig.RESPONSE_EXCHANGE_NAME;
import static ru.dbelokursky.config.RabbitmqConfig.RESPONSE_ROUTING_KEY;

@Slf4j
@RequiredArgsConstructor
@Component
public class Receiver {

    private final StockService service;
    private final RabbitTemplate rabbitTemplate;

    public void receiveMessage(Message message) {
        Message responseMessage = Message.builder()
                .chatId(message.getChatId())
                .stockDto(service.getStockInfo().get(0))
                .build();

        rabbitTemplate.convertAndSend(RESPONSE_EXCHANGE_NAME, RESPONSE_ROUTING_KEY, responseMessage);
        log.info("Received <{}>", message);
    }

}
