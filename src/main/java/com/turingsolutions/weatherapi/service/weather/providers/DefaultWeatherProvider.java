package com.turingsolutions.weatherapi.service.weather.providers;

import com.turingsolutions.weatherapi.service.weather.interfaces.WeatherProvider;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

public abstract class DefaultWeatherProvider<T> implements WeatherProvider<T> {
    private String apiKey;
    private String url;
    private RestTemplate restTemplate;

    public String getApiKey() {
        return apiKey;
    }

    public String getUrl() {
        return url;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DefaultWeatherProvider(String apiKey, String url, RestTemplate restTemplate) {
        this.apiKey = apiKey;
        this.url = url;
        this.restTemplate = restTemplate;
    }
}
