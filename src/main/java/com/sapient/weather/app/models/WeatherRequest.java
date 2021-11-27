package com.sapient.weather.app.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WeatherRequest {

    @JsonProperty("cityName")
    private String cityName;

    @JsonCreator
    public WeatherRequest(WeatherRequestBuilder builder) {
        this.cityName = builder.cityName;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class WeatherRequestBuilder {

        @JsonProperty("cityName")
        private String cityName;

        private WeatherRequestBuilder() {

        }

        public static WeatherRequestBuilder getInstance() {
            return new WeatherRequestBuilder();
        }

        public WeatherRequestBuilder setCityName(String cityName) {
            this.cityName = cityName;
            return this;
        }

        public WeatherRequest build() {
            return new WeatherRequest(this);
        }
    }
}
