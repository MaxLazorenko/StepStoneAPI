package com.turingsolutions.weatherapi.service.weather;

import com.turingsolutions.weatherapi.config.stepstone.StepStoneConfiguration;
import com.turingsolutions.weatherapi.service.weather.interfaces.WeatherProvider;
import com.turingsolutions.weatherapi.service.weather.providers.*;
import org.springframework.web.client.RestTemplate;

public class WeatherProviderFactory {

    public static <T> WeatherProvider<T> createProviderByType(WeatherProviderType type, StepStoneConfiguration configuration) {
        DefaultWeatherProvider provider = null;
        switch (type) {
            case OPEN_WEATHER:
                provider = new OpenWeatherProvider(configuration.getOpenWeatherKey(),
                                                   configuration.getOpenWeatherUrl(),
                                                   new RestTemplate());
                break;
            case WEATHER_BIT:
                provider = new BitWeatherProvider(configuration.getBitWeatherKey(),
                                                  configuration.getBitWeatherUrl(),
                                                  new RestTemplate());
                break;
            case ACCU_WEATHER:
                provider = new AccuWeatherProvider(configuration.getAccuWeatherKey(),
                                                   configuration.getAccuWeatherUrl(),
                                                   new RestTemplate());
        }
        return provider;
    }
}
