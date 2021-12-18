package ru.dbelokursky.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TickerEnum {
    IDCC("IDCC", "InterDigItal Inc"),
    SGEN("SGEN", "Seagen Inc."),
    NAVI("NAVI", "Navient"),
    WGO("WGO", "Winnebago Industries Inc"),
    MDMG("MDMG", "ГК Мать и дитя"),
    RHI("RHI", "Robert Half"),
    RH("RH", "RH");

    public final String ticker;
    public final String description;

}
