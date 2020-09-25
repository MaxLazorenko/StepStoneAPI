package com.turingsolutions.weatherapi.service.weather.providers;

import java.net.URISyntaxException;

public abstract class DefaultWeatherProvider<T> {
    private String apiKey;
    private String url;

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

    public DefaultWeatherProvider(String apiKey, String url) {
        this.apiKey = apiKey;
        this.url = url;
    }

    public abstract T getWeatherByCity(String city) throws URISyntaxException;
}
