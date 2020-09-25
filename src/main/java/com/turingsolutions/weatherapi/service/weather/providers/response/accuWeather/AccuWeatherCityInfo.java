package com.turingsolutions.weatherapi.service.weather.providers.response.accuWeather;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccuWeatherCityInfo {

    private String key;

    @JsonProperty("Key")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public AccuWeatherCityInfo(String key) {
        this.key = key;
    }
    public AccuWeatherCityInfo() {

    }
}
