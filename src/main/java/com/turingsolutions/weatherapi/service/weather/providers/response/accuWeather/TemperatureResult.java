package com.turingsolutions.weatherapi.service.weather.providers.response.accuWeather;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TemperatureResult {
    private long value;
    private String unit;

    @JsonProperty("Value")
    public long getValue() {
        return value;
    }
    @JsonProperty("Unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setValue(long value) {
        this.value = value;
    }
    public TemperatureResult(long value, String unit) {
        this.value = value;
        this.unit = unit;
    }
    public TemperatureResult() {
        this(0, null);
    }
}
