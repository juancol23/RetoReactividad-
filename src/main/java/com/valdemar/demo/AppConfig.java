package com.valdemar.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
    @Value("${config.base.endpoint}")
    private String urlBase;

    @Bean
    public WebClient registrarWebClient(){
        return WebClient.create(urlBase);
    }
}
