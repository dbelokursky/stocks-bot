package ru.dbelokursky.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class Message implements Serializable {

    private final String chatId;
    private final StockDto stockDto;
}
