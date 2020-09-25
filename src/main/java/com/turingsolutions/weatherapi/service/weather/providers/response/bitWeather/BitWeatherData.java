package com.turingsolutions.weatherapi.service.weather.providers.response.bitWeather;

public class BitWeatherData {
    private double temp;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public BitWeatherData(double temp) {
        this.temp = temp;
    }
    public BitWeatherData() {

    }
}
