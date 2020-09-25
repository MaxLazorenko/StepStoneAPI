package com.turingsolutions.weatherapi.service.weather.providers.response.accuWeather;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Temperature {
    private TemperatureResult minimum;
    private TemperatureResult maximum;

    @JsonProperty("Maximum")
    public TemperatureResult getMaximum() {
        return maximum;
    }

    @JsonProperty("Minimum")
    public TemperatureResult getMinimum() {
        return minimum;
    }

    public void setMaximum(TemperatureResult maximum) {
        this.maximum = maximum;
    }

    public void setMinimum(TemperatureResult minimum) {
        this.minimum = minimum;
    }

    public Temperature(TemperatureResult minimum, TemperatureResult maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }
    public Temperature() {
        this(null, null);
    }
}
