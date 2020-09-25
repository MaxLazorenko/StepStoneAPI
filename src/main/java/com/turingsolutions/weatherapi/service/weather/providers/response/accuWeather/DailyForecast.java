package com.turingsolutions.weatherapi.service.weather.providers.response.accuWeather;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DailyForecast {
    private Temperature temperature;

    @JsonProperty("Temperature")
    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }
    public DailyForecast(Temperature temperature) {
        this.temperature = temperature;
    }
    public DailyForecast() {
        this(null);
    }
}
