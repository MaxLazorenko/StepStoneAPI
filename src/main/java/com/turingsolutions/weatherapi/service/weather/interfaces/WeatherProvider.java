package com.turingsolutions.weatherapi.service.weather.interfaces;

import java.net.URISyntaxException;

public interface WeatherProvider<T> {
    T getWeatherByCity(String city) throws URISyntaxException;
}
