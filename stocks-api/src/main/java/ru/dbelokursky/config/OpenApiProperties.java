package ru.dbelokursky.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app.openapi")
public class OpenApiProperties {
    private String token;
}
