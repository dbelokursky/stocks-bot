package ru.dbelokursky.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dbelokursky.dto.StockDto;
import ru.dbelokursky.service.StockInfoServiceImpl;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class StocksController {

    private final StockInfoServiceImpl stockInfoService;

    @GetMapping(value = "/stock")
    public List<StockDto> stockInfo() {
        return stockInfoService.getStockInfo();
    }

}
