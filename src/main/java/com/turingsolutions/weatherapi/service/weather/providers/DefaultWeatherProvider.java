package com.turingsolutions.weatherapi.service.weather.providers;

import com.turingsolutions.weatherapi.service.weather.interfaces.WeatherProvider;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

public abstract class DefaultWeatherProvider<T> implements WeatherProvider<T> {
    private final String apiKey;
    private final String url;
    private final RestTemplate restTemplate;

    public String getApiKey() {
        return apiKey;
    }

    public String getUrl() {
        return url;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public DefaultWeatherProvider(String apiKey, String url, RestTemplate restTemplate) {
        this.apiKey = apiKey;
        this.url = url;
        this.restTemplate = restTemplate;
    }
}
