package com.turingsolutions.weatherapi.service.weather.interfaces;

import com.turingsolutions.weatherapi.service.dto.StepStoneRequest;
import com.turingsolutions.weatherapi.service.dto.StepStoneResult;

import java.net.URISyntaxException;
import java.util.List;

public interface WeatherService {
    List<StepStoneResult> getWeather(List<StepStoneRequest> request) throws URISyntaxException;
}
