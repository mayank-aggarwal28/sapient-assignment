package com.sapient.weather.app.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Day {

    private double maxTemp_C;
    private double maxTemp_F;
    private double minTemp_C;
    private double minTemp_F;
    private double avgTemp_C;
    private double avgTemp_F;
    private double maxWind_Mph;
    private double maxWind_Kph;
    private double totalPrecipitation_MM;
    private double totalPrecipitation_IN;
    private double avgVis_KM;
    private double avgVis_Miles;
    private double avgHumidity;
    private int dailyWillItRain;
    private int dailyChanceOfRain;
    private int dailyWillItSnow;
    private int dailyChanceOfSnow;
    private Condition condition;
    private double uv;

    @JsonCreator
    public Day(DayBuilder builder) {
        this.maxTemp_C = builder.maxTemp_C;
        this.maxTemp_F = builder.maxTemp_F;
        this.minTemp_C = builder.minTemp_C;
        this.minTemp_F = builder.minTemp_F;
        this.avgTemp_C = builder.avgTemp_C;
        this.avgTemp_F = builder.avgTemp_F;
        this.maxWind_Mph = builder.maxWind_Mph;
        this.maxWind_Kph = builder.maxWind_Kph;
        this.totalPrecipitation_MM = builder.totalPrecipitation_MM;
        this.totalPrecipitation_IN = builder.totalPrecipitation_IN;
        this.avgVis_KM = builder.avgVis_KM;
        this.avgVis_Miles = builder.avgVis_Miles;
        this.avgHumidity = builder.avgHumidity;
        this.dailyWillItRain = builder.dailyWillItRain;
        this.dailyChanceOfRain = builder.dailyChanceOfRain;
        this.dailyWillItSnow = builder.dailyWillItSnow;
        this.dailyChanceOfSnow = builder.dailyChanceOfSnow;
        this.condition = builder.condition;
        this.uv = builder.uv;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class DayBuilder {

        @JsonProperty("maxtemp_c")
        private double maxTemp_C;

        @JsonProperty("maxtemp_f")
        private double maxTemp_F;

        @JsonProperty("mintemp_c")
        private double minTemp_C;

        @JsonProperty("mintemp_f")
        private double minTemp_F;

        @JsonProperty("avgtemp_c")
        private double avgTemp_C;

        @JsonProperty("avgtemp_f")
        private double avgTemp_F;

        @JsonProperty("maxwind_mph")
        private double maxWind_Mph;

        @JsonProperty("maxwind_kph")
        private double maxWind_Kph;

        @JsonProperty("totalprecip_mm")
        private double totalPrecipitation_MM;

        @JsonProperty("totalprecip_in")
        private double totalPrecipitation_IN;

        @JsonProperty("avgvis_km")
        private double avgVis_KM;

        @JsonProperty("avgvis_miles")
        private double avgVis_Miles;

        @JsonProperty("avghumidity")
        private double avgHumidity;

        @JsonProperty("daily_will_it_rain")
        private int dailyWillItRain;

        @JsonProperty("daily_chance_of_rain")
        private int dailyChanceOfRain;

        @JsonProperty("daily_will_it_snow")
        private int dailyWillItSnow;

        @JsonProperty("daily_chance_of_snow")
        private int dailyChanceOfSnow;

        @JsonProperty("condition")
        private Condition condition;

        @JsonProperty("uv")
        private double uv;

        private DayBuilder() {

        }

        public static DayBuilder newInstance() {
            return new DayBuilder();
        }

        public DayBuilder setMaxTempC(double maxTemp_C) {
            this.maxTemp_C = maxTemp_C;
            return this;
        }

        public DayBuilder setMaxTempF(double maxTemp_F) {
            this.maxTemp_F = maxTemp_F;
            return this;
        }

        public DayBuilder setMinTempC(double minTemp_C) {
            this.minTemp_C = minTemp_C;
            return this;
        }

        public DayBuilder setMinTempF(double minTemp_F) {
            this.minTemp_F = minTemp_F;
            return this;
        }

        public DayBuilder setAvgTempC(double avgTemp_C) {
            this.avgTemp_C = avgTemp_C;
            return this;
        }

        public DayBuilder setAvgTempF(double avgTemp_F) {
            this.avgTemp_F = avgTemp_F;
            return this;
        }

        public DayBuilder setMaxWindMph(double maxWind_Mph) {
            this.maxWind_Mph = maxWind_Mph;
            return this;
        }

        public DayBuilder setMaxWindKph(double maxWind_Kph) {
            this.maxWind_Kph = maxWind_Kph;
            return this;
        }

        public DayBuilder setTotalPrecipitationMM(double totalPrecipitation_MM) {
            this.totalPrecipitation_MM = totalPrecipitation_MM;
            return this;
        }

        public DayBuilder setTotalPrecipitationIN(double totalPrecipitation_IN) {
            this.totalPrecipitation_IN = totalPrecipitation_IN;
            return this;
        }

        public DayBuilder setAvgVisKM(double avgVis_KM) {
            this.avgVis_KM = avgVis_KM;
            return this;
        }

        public DayBuilder setAvgVisMiles(double avgVis_Miles) {
            this.avgVis_Miles = avgVis_Miles;
            return this;
        }

        public DayBuilder setAvgHumidity(double avgHumidity) {
            this.avgHumidity = avgHumidity;
            return this;
        }

        public DayBuilder setDailyWillItRain(int dailyWillItRain) {
            this.dailyWillItRain = dailyWillItRain;
            return this;
        }

        public DayBuilder setDailyChanceOfRain(int dailyChanceOfRain) {
            this.dailyChanceOfRain = dailyChanceOfRain;
            return this;
        }

        public DayBuilder setDailyWillItSnow(int dailyWillItSnow) {
            this.dailyWillItSnow = dailyWillItSnow;
            return this;
        }

        public DayBuilder setDailyChanceOfSnow(int dailyChanceOfSnow) {
            this.dailyChanceOfSnow = dailyChanceOfSnow;
            return this;
        }

        public DayBuilder setCondition(Condition condition) {
            this.condition = condition;
            return this;
        }

        public DayBuilder setUV(double uv) {
            this.uv = uv;
            return this;
        }

        public Day build() {
            return new Day(this);
        }
    }
}
