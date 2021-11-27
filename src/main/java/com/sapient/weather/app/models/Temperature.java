package com.sapient.weather.app.models;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Temperature {

    private double minimumTemperature;

    private double maximumTemperature;

    public Temperature(TemperatureBuilder builder) {
        this.minimumTemperature = builder.minimumTemperature;
        this.maximumTemperature = builder.maximumTemperature;
    }

    public static class TemperatureBuilder {

        private double minimumTemperature;

        private double maximumTemperature;

        private TemperatureBuilder() {

        }

        public static TemperatureBuilder getInstance() {
            return new TemperatureBuilder();
        }

        public TemperatureBuilder setMinimumTemperature(double minimumTemperature) {
            this.minimumTemperature = minimumTemperature;
            return this;
        }

        public TemperatureBuilder setMaximumTemperature(double maximumTemperature) {
            this.maximumTemperature = maximumTemperature;
            return this;
        }

        public Temperature build() {
            return new Temperature(this);
        }
    }
}
