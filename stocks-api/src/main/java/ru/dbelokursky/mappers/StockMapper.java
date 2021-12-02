package ru.dbelokursky.mappers;

import org.mapstruct.Mapper;
import ru.dbelokursky.dto.StockDto;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrument;

@Mapper(componentModel = "spring")
public interface StockMapper {

    StockDto marketInstrumentToStockDto(MarketInstrument marketInstrument);
}
