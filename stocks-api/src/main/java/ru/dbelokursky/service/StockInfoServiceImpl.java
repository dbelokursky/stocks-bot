package ru.dbelokursky.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.dbelokursky.dto.StockDto;
import ru.dbelokursky.mappers.StockMapper;
import ru.tinkoff.invest.openapi.OpenApi;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockInfoServiceImpl implements StockService {

    private final OpenApi api;
    private final StockMapper stockMapper;
    private final Map<String, StockDto> stocks = new HashMap<>(2000);

    @PostConstruct
    @SneakyThrows
    private void init() {
        api.getMarketContext().getMarketStocks().get().getInstruments().stream()
                .map(stockMapper::marketInstrumentToStockDto)
                .forEach(s -> stocks.put(s.getTicker(), s));
    }


    @Override
    public Map<String, StockDto> getAllStocksInfo() {
        return stocks;
    }

    @Override
    public List<StockDto> getStockInfoByTicker(String ticker) {
        return List.of(stocks.get(ticker));
    }
}
