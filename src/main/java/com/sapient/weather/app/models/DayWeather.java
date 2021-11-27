package com.sapient.weather.app.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DayWeather {

    private String date;
    private long dateEpoch;
    private Day day;

    @JsonCreator
    public DayWeather(DayWeatherBuilder builder) {
        this.date = builder.date;
        this.dateEpoch = builder.dateEpoch;
        this.day = builder.day;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class DayWeatherBuilder {

        @JsonProperty("date")
        private String date;

        @JsonProperty("date_epoch")
        private long dateEpoch;

        @JsonProperty("day")
        private Day day;

        private DayWeatherBuilder() {

        }

        public static DayWeatherBuilder newInstance() {
            return new DayWeatherBuilder();
        }

        public DayWeatherBuilder setDate(String date) {
            this.date = date;
            return this;
        }

        public DayWeatherBuilder setDateEpoch(long dateEpoch) {
            this.dateEpoch = dateEpoch;
            return this;
        }

        public DayWeatherBuilder setDay(Day day) {
            this.day = day;
            return this;
        }

        public DayWeather build() {
            return new DayWeather(this);
        }
    }
}
