package ru.dbelokursky.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Builder
@Data
public class BotMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String chatId;
    private final StockDto stockDto;
}
