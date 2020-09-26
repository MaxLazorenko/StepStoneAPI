package com.turingsolutions.weatherapi.web.rest;

import com.turingsolutions.weatherapi.service.dto.StepStoneRequestDTO;
import com.turingsolutions.weatherapi.service.dto.StepStoneResultDTO;
import com.turingsolutions.weatherapi.service.weather.interfaces.WeatherService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/weather")
@Validated
public class WeatherResource {
    private final WeatherService service;

    public WeatherResource(WeatherService service) {
        this.service = service;
    }

    @PostMapping("/")
    public List<StepStoneResultDTO> getWeather(@Valid @NotNull @RequestBody List<@Valid StepStoneRequestDTO> requests) throws URISyntaxException {
        return this.service.getWeather(requests);
    }
}
