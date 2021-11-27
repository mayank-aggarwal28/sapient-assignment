package com.sapient.weather.app.clients;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.sapient.weather.app.config.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class InMemoryCache {

    private final ApplicationConfig applicationConfig;

    @Autowired
    public InMemoryCache(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    @Bean
    public Caffeine caffeineConfig() {
        return Caffeine.newBuilder().expireAfterWrite(applicationConfig.getCacheExpiryHours(), TimeUnit.HOURS);
    }

    @Bean
    public CacheManager cacheManager(Caffeine caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.getCache("forecast_cache");
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }
}
