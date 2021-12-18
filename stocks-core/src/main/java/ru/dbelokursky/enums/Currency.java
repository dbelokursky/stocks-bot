package ru.dbelokursky.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Currency {
    USD("USD", "Доллар США"),
    RUB("RUB", "Российский рубль");

    private final String name;
    private final String description;
}
