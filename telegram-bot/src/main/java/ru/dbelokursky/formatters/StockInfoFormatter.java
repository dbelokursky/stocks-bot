package ru.dbelokursky.formatters;

import lombok.experimental.UtilityClass;
import ru.dbelokursky.dto.StockDto;

@UtilityClass
public class StockInfoFormatter {

    public static String format(StockDto stockDto) {
        return String.format("""
                Название: %s
                %s: %s
                %s: %s
                %s: %s
                Валюта: %s
                """,
                stockDto.getName(),
                StockDto.Fields.ticker, stockDto.getTicker(),
                StockDto.Fields.isin, stockDto.getIsin(),
                StockDto.Fields.figi, stockDto.getFigi(),
                stockDto.getCurrency());
    }
}
