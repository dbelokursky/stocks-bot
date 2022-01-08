package ru.dbelokursky.service;

import ru.dbelokursky.dto.StockDto;

import java.util.List;
import java.util.Map;

public interface StockService {

    Map<String, StockDto> getAllStocksInfo();

    List<StockDto> getStockInfoByTicker(String ticker);

}
