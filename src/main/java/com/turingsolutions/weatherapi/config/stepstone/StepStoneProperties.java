package com.turingsolutions.weatherapi.config.stepstone;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "stepstone", ignoreUnknownFields = false)
public class StepStoneProperties {
    private String accuWeather;
    private String openWeather;
    private String weatherBit;

    public String getAccuWeather() {
        return accuWeather;
    }

    public String getOpenWeather() {
        return openWeather;
    }

    public String getWeatherBit() {
        return weatherBit;
    }

    public void setAccuWeather(String accuWeather) {
        this.accuWeather = accuWeather;
    }

    public void setOpenWeather(String openWeather) {
        this.openWeather = openWeather;
    }

    public void setWeatherBit(String weatherBit) {
        this.weatherBit = weatherBit;
    }
}
