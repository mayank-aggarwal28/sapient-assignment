package com.sapient.weather.app.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Condition {

    private String text;
    private String iconUrl;
    private int code;

    @JsonCreator
    public Condition(ConditionBuilder builder) {
        this.text = builder.text;
        this.iconUrl = builder.iconUrl;
        this.code = builder.code;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class ConditionBuilder {

        @JsonProperty("text")
        private String text;

        @JsonProperty("icon")
        private String iconUrl;

        @JsonProperty("code")
        private int code;

        private ConditionBuilder() {

        }

        public ConditionBuilder newInstance() {
            return new ConditionBuilder();
        }

        public ConditionBuilder setText(String text) {
            this.text = text;
            return this;
        }

        public ConditionBuilder setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
            return this;
        }

        public ConditionBuilder setCode(int code) {
            this.code = code;
            return this;
        }

        public Condition build() {
            return new Condition(this);
        }
    }
}
