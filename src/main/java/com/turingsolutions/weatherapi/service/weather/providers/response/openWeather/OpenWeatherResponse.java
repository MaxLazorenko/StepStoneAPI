package com.turingsolutions.weatherapi.service.weather.providers.response.openWeather;

public class OpenWeatherResponse {
    private OpenWeatherMain main;

    public OpenWeatherMain getMain() {
        return main;
    }

    public void setMain(OpenWeatherMain main) {
        this.main = main;
    }

    public OpenWeatherResponse(OpenWeatherMain openWeatherMain) {
        this.main = openWeatherMain;
    }
    public OpenWeatherResponse() {

    }
}
