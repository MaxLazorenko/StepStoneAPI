package com.turingsolutions.weatherapi.service.weather.providers.response.accuWeather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AccuWeatherResponse {
    private List<DailyForecast> dailyForecasts;

    @JsonProperty("DailyForecasts")
    public List<DailyForecast> getDailyForecast() {
        return dailyForecasts;
    }

    public void setDailyForecast(List<DailyForecast> dailyForecast) {
        this.dailyForecasts = dailyForecast;
    }

    public AccuWeatherResponse(List<DailyForecast> dailyForecast) {
        this.dailyForecasts = dailyForecast;
    }
    public AccuWeatherResponse() { }
}

