package ru.dbelokursky.service;

import ru.dbelokursky.dto.StockDto;

import java.util.stream.Stream;

public interface StockService {

    Stream<StockDto> getAllStocksInfo();

    StockDto getStockInfoByTicker(String ticker);

}
