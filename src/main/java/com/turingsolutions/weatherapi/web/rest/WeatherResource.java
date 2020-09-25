package com.turingsolutions.weatherapi.web.rest;

import com.turingsolutions.weatherapi.service.dto.StepStoneRequest;
import com.turingsolutions.weatherapi.service.dto.StepStoneResult;
import com.turingsolutions.weatherapi.service.weather.interfaces.WeatherService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/weather")
public class WeatherResource {
    private final WeatherService service;

    public WeatherResource(WeatherService service) {
        this.service = service;
    }

    @RequestMapping
    public List<StepStoneResult> getWeather(@RequestBody List<StepStoneRequest> requests) throws URISyntaxException {
        return this.service.getWeather(requests);
    }
}
