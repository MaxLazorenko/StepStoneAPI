package com.turingsolutions.weatherapi.service.dto;

import com.turingsolutions.weatherapi.service.weather.WeatherProviderType;

import java.util.List;

public class StepStoneRequest {
    private WeatherProviderType providerType;
    private List<String> citiesList;

    public List<String> getCitiesList() {
        return citiesList;
    }

    public void setCitiesList(List<String> citiesList) {
        this.citiesList = citiesList;
    }

    public WeatherProviderType getProviderType() {
        return providerType;
    }

    public void setProviderType(WeatherProviderType providerType) {
        this.providerType = providerType;
    }

    public StepStoneRequest(WeatherProviderType providerType, List<String> citiesList) {
        this.providerType = providerType;
        this.citiesList = citiesList;
    }
    public StepStoneRequest() {
        this(null ,null);
    }
}
