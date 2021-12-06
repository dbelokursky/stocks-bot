package ru.dbelokursky.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dbelokursky.config.RabbitmqConfig;
import ru.dbelokursky.dto.StockDto;
import ru.dbelokursky.service.StockService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class StocksController {

    private final StockService stockInfoService;
    private final RabbitTemplate rabbitTemplate;


    @GetMapping(value = "/stock")
    public List<StockDto> stockInfo() {
        rabbitTemplate.convertAndSend(RabbitmqConfig.RESPONSE_EXCHANGE_NAME, RabbitmqConfig.RESPONSE_ROUTING_KEY,  stockInfoService.getStockInfo().get(0));
        return null;
//        return stockInfoService.getStockInfo();
    }

}
