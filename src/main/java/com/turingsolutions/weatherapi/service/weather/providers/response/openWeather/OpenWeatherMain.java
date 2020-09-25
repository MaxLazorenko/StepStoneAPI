package com.turingsolutions.weatherapi.service.weather.providers.response.openWeather;

public class OpenWeatherMain {
    private double temp;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }
    public OpenWeatherMain(double temp) {
        this.temp = temp;
    }
    public OpenWeatherMain() {

    }
}
