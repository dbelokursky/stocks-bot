package ru.dbelokursky.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app.telegram")
public class TelegramProperties {
    private String token;
    private String name;
}
