package ru.dbelokursky.enums;

public enum CommandEnum {
    GET_STOCK_PRICE("get_stock_price", "Получить стоимость акции");

    public final String name;
    public final String description;

    CommandEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
