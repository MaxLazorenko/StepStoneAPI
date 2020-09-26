package com.turingsolutions.weatherapi.service.weather.interfaces;

import com.turingsolutions.weatherapi.service.dto.StepStoneRequestDTO;
import com.turingsolutions.weatherapi.service.dto.StepStoneResultDTO;

import java.net.URISyntaxException;
import java.util.List;

public interface WeatherService {
    List<StepStoneResultDTO> getWeather(List<StepStoneRequestDTO> request) throws URISyntaxException;
}
