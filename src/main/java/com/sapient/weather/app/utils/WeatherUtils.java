package com.sapient.weather.app.utils;

import com.sapient.weather.app.Constants;
import com.sapient.weather.app.config.ApplicationConfig;
import okhttp3.HttpUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

@Service
public class WeatherUtils {

    private final ApplicationConfig applicationConfig;

    @Autowired
    public WeatherUtils(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    public String buildURL(String baseURL, Map<String, String> queryParams) {

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(baseURL)).newBuilder();

        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }

        return urlBuilder.build().toString();
    }

    public LocalDate returnParsedDate(String dateString) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Constants.INPUT_DATE_FORMAT);
        return LocalDate.parse(dateString, dateTimeFormatter);
    }
}
