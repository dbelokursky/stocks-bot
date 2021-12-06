package ru.dbelokursky.service;

import ru.dbelokursky.dto.StockDto;

import java.util.List;

public interface StockService {

    List<StockDto> getStockInfo();
}
