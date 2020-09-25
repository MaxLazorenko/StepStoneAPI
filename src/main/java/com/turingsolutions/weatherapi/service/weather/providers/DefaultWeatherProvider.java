package com.turingsolutions.weatherapi.service.weather.providers;

import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

public abstract class DefaultWeatherProvider<T> {
    private String apiKey;
    private String url;
    private final RestTemplate restTemplate;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public DefaultWeatherProvider(String apiKey, String url, RestTemplate restTemplate) {
        this.apiKey = apiKey;
        this.url = url;
        this.restTemplate = restTemplate;
    }

    public abstract T getWeatherByCity(String city) throws URISyntaxException;
}
