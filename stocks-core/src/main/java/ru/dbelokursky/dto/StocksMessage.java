package ru.dbelokursky.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Getter
@SuperBuilder
public class StocksMessage extends StocksAbstractMessage implements Serializable {
    private final List<StockDto> stocks;
}
