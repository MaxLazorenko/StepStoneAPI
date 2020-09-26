package com.turingsolutions.weatherapi.service.dto;

import com.turingsolutions.weatherapi.service.weather.providers.WeatherProviderType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class StepStoneRequestDTO {

    @NotNull(message = "StepStone request can't be without type")
    private WeatherProviderType providerType;
    @NotNull(message = "StepStone request can't be without city list")
    @NotEmpty(message = "Cities list can't be empty")
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

    public StepStoneRequestDTO(WeatherProviderType providerType, List<String> citiesList) {
        this.providerType = providerType;
        this.citiesList = citiesList;
    }
    public StepStoneRequestDTO withType(WeatherProviderType providerType) {
        this.providerType = providerType;
        return this;
    }
    public StepStoneRequestDTO() {
        this(null ,null);
    }
}
