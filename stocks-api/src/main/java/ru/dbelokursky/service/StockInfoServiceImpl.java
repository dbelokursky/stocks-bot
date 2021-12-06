package ru.dbelokursky.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.dbelokursky.dto.StockDto;
import ru.dbelokursky.mappers.StockMapper;
import ru.tinkoff.invest.openapi.OpenApi;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockInfoServiceImpl implements StockService {

    private final OpenApi api;
    private final StockMapper stockMapper;

    @Override
    public List<StockDto> getStockInfo() {
        List<StockDto> instruments;
        try {
            instruments = api.getMarketContext().getMarketStocks().get().getInstruments().stream()
                    .map(stockMapper::marketInstrumentToStockDto)
                    .toList();

        } catch (InterruptedException | ExecutionException e) {
            log.error("Во время получения информации о бумагах произошла ошибка: {}", e.getLocalizedMessage(), e);
            throw new RuntimeException(e);
        }
        return instruments;
    }
}
