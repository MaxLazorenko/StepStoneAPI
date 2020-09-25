package com.turingsolutions.weatherapi.service.weather.providers.response.bitWeather;

import java.util.List;

public class BitWeatherResponse {
    private List<BitWeatherData> data;

    public List<BitWeatherData> getData() {
        return data;
    }

    public void setData(List<BitWeatherData> data) {
        this.data = data;
    }

    public BitWeatherResponse(List<BitWeatherData> data) {
        this.data = data;
    }
    public BitWeatherResponse() {

    }
}
