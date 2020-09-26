package com.turingsolutions.weatherapi.service.weather;

import com.turingsolutions.weatherapi.config.stepstone.StepStoneConfiguration;
import com.turingsolutions.weatherapi.service.weather.interfaces.WeatherProvider;
import com.turingsolutions.weatherapi.service.weather.providers.*;
import org.springframework.web.client.RestTemplate;

public class WeatherProviderFactory {

    public static <T> WeatherProvider<T> createProviderByType(WeatherProviderType type, StepStoneConfiguration configuration, RestTemplate template) {
        if (type == WeatherProviderType.OPEN_WEATHER) {
            return (WeatherProvider<T>) new OpenWeatherProvider(configuration.getOpenWeatherKey(),
                                        configuration.getOpenWeatherUrl(),
                                        template);
        }
        if (type == WeatherProviderType.ACCU_WEATHER) {
            return (WeatherProvider<T>) new AccuWeatherProvider(configuration.getAccuWeatherKey(),
                                        configuration.getAccuWeatherUrl(),
                                        template);
        }
        if (type == WeatherProviderType.WEATHER_BIT) {
            return (WeatherProvider<T>) new BitWeatherProvider(configuration.getBitWeatherKey(),
                                        configuration.getBitWeatherUrl(),
                                        template);
        }

        return null;
    }
}
