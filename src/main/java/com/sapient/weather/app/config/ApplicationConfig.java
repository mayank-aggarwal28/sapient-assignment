package com.sapient.weather.app.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "weatherapp")
@Getter
@Setter
public class ApplicationConfig {

    private String requestUrl;

    private String encryptionScheme;

    private int encryptionBits;

    private String appid;

    private String days;

    private int cacheExpiryHours;
}
