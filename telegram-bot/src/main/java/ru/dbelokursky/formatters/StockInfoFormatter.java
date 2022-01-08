package ru.dbelokursky.formatters;

import lombok.experimental.UtilityClass;
import ru.dbelokursky.dto.StockDto;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class StockInfoFormatter {

    private static final String STOCK_TEMPLATE =
            """
            Название: %s
            %s: %s
            %s: %s
            %s: %s
            Валюта: %s
            """;

    public static String format(List<StockDto> stockDtos) {
        return stockDtos.stream()
                .map(s ->
                        String.format(STOCK_TEMPLATE,
                                s.getName(),
                                StockDto.Fields.ticker, s.getTicker(),
                                StockDto.Fields.isin, s.getIsin(),
                                StockDto.Fields.figi, s.getFigi(),
                                s.getCurrency())
                )
                .collect(Collectors.joining(","));
    }
}
