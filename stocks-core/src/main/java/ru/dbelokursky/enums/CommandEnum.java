package ru.dbelokursky.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.util.Arrays;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CommandEnum {
    GET_STOCK_PRICE("/get_stock_price", "Получить стоимость акции"),
    GET_STOCK_INFO("/get_stock_info", "Получить информацию о бумаге (ticker, figi ..)");

    public final String name;
    private final String description;

    CommandEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static CommandEnum getEnumByCommandName(String commandName) {
        return Arrays.stream(values())
                .filter(v -> v.name.equals(commandName))
                .findAny()
                .orElseThrow();
    }
}
