package ru.dbelokursky.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.okhttp.OkHttpOpenApi;

@Slf4j
@Configuration
@EnableConfigurationProperties({OpenApiProperties.class})
@RequiredArgsConstructor
public class AppConfig {

    private final OpenApiProperties openApiConfig;

    @Bean
    public OpenApi openApi() {
        return new OkHttpOpenApi(openApiConfig.getToken(), Boolean.TRUE);
    }
}
