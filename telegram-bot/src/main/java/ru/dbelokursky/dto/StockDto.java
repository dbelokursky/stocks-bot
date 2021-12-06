package ru.dbelokursky.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class StockDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Financial Instrument Global Identifier. Уникальный идентификатор финансовых инструментов,
     * который может быть назначен инструментам.Википедия  site:star-wiki.ru
     */
    private final String figi;

    /**
     * Краткое название в биржевой информации котируемых инструментов (акций, облигаций, индексов).
     * Является уникальным идентификатором в рамках одной биржи или информационной системы.
     */
    private final String ticker;

    /**
     * Название бумаги.
     */
    private final String name;

    /**
     * Международный идентификационный код ценной бумаги (англ. International Securities Identification Number,
     * общепринятое сокращение — ISIN) — 12-разрядный буквенно-цифровой код, однозначно идентифицирующий ценную бумагу.
     */
    private final String isin;

    /**
     * Валюта в которой торгуется бумага.
     */
    private final String currency;

}
