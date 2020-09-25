package com.turingsolutions.weatherapi.config.stepstone;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(StepStoneProperties.class)
public class StepStoneConfiguration {
    private final StepStoneProperties properties;

    public StepStoneConfiguration(StepStoneProperties properties) {
        this.properties = properties;
    }

    public String getAccuWeatherKey() {
        return properties.getAccuWeather().contains(";") ?
               properties.getAccuWeather().split(";")[0] :
               "hKHpptfitzDYGTQaVIpjTD8YAwoVOt1D";
    }
    public String getAccuWeatherUrl() {
        return properties.getAccuWeather().contains(";") ?
               properties.getAccuWeather().split(";")[1] :
               "http://dataservice.accuweather.com";
    }
    public String getOpenWeatherKey() {
        return properties.getOpenWeather().contains(";") ?
               properties.getOpenWeather().split(";")[0] :
               "c7aa6f8d844fe9c0790d35021b5f89f1";
    }
    public String getOpenWeatherUrl() {
        return properties.getOpenWeather().contains(";") ?
               properties.getOpenWeather().split(";")[1] :
               "http://api.openweathermap.org/data/2.5/weather";
    }
    public String getBitWeatherKey() {
        return properties.getWeatherBit().contains(";") ?
               properties.getWeatherBit().split(";")[0] :
               "161b00ebc51c4d2ab7ffac3000b2bf66";
    }
    public String getBitWeatherUrl() {
        return properties.getWeatherBit().contains(";") ?
               properties.getWeatherBit().split(";")[1] :
               "http://api.weatherbit.io/v2.0/current";
    }
}
