package ru.dbelokursky.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.okhttp.OkHttpOpenApi;

@Slf4j
@Configuration
public class AppConfig {

    @Value("${app.openapi.token}")
    private String openapiToken;

    @Bean
    public OpenApi openApi() {
        return new OkHttpOpenApi(openapiToken, Boolean.TRUE);
    }
}
