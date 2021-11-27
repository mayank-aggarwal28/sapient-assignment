package com.sapient.weather.app.models;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class WeatherResponse {

    private LocalDate date;
    private String dayOfWeek;
    private Temperature forecast;
    private String status;

    public WeatherResponse(WeatherResponseBuilder builder) {
        this.date = builder.date;
        this.dayOfWeek = builder.dayOfWeek;
        this.forecast = builder.forecast;
        this.status = builder.status;
    }

    public static class WeatherResponseBuilder {

        private LocalDate date;
        private String dayOfWeek;
        private Temperature forecast;
        private String status;

        private WeatherResponseBuilder() {

        }

        public static WeatherResponseBuilder newInstance() {
            return new WeatherResponseBuilder();
        }

        public WeatherResponseBuilder setDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public WeatherResponseBuilder setDayOfWeek(String dayOfWeek) {
            this.dayOfWeek = dayOfWeek;
            return this;
        }

        public WeatherResponseBuilder setForecast(Temperature forecast) {
            this.forecast = forecast;
            return this;
        }

        public WeatherResponseBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public WeatherResponse build() {
            return new WeatherResponse(this);
        }
    }
}
