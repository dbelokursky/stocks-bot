package ru.dbelokursky.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Data
@SuperBuilder
public abstract class StocksAbstractMessage implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String chatId;
}
