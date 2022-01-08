package ru.dbelokursky.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@SuperBuilder
public class StockRequestMessage extends StocksAbstractMessage implements Serializable {
    private final String ticker;
    private final String command;
}
