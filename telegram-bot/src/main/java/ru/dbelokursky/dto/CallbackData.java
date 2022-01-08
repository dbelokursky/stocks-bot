package ru.dbelokursky.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class CallbackData {
    private final String ticker;
    private final String command;

    public CallbackData() {
        this.ticker = null;
        this.command = null;
    }
}
