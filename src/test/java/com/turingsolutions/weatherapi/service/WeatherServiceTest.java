package com.turingsolutions.weatherapi.service;

import com.turingsolutions.weatherapi.config.stepstone.StepStoneConfiguration;
import com.turingsolutions.weatherapi.config.stepstone.StepStoneProperties;
import com.turingsolutions.weatherapi.service.dto.StepStoneRequestDTO;
import com.turingsolutions.weatherapi.service.dto.StepStoneResultDTO;
import com.turingsolutions.weatherapi.service.weather.WeatherProviderFactory;
import com.turingsolutions.weatherapi.service.weather.WeatherServiceImpl;
import com.turingsolutions.weatherapi.service.weather.interfaces.WeatherProvider;
import com.turingsolutions.weatherapi.service.weather.interfaces.WeatherService;
import com.turingsolutions.weatherapi.service.weather.providers.AccuWeatherProvider;
import com.turingsolutions.weatherapi.service.weather.providers.WeatherProviderType;
import com.turingsolutions.weatherapi.service.weather.providers.response.accuWeather.AccuWeatherResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {
    private WeatherService service;
    private StepStoneConfiguration configuration;
    private StepStoneProperties properties;

    @BeforeEach
    void SetUp() {
        this.properties = new StepStoneProperties();
        properties.setAccuWeather("hKHpptfitzDYGTQaVIpjTD8YAwoVOt1D;http://dataservice.accuweather.com");
        properties.setOpenWeather("c7aa6f8d844fe9c0790d35021b5f89f1;http://api.openweathermap.org/data/2.5/weather");
        properties.setWeatherBit("161b00ebc51c4d2ab7ffac3000b2bf66;http://api.weatherbit.io/v2.0/current");
        this.configuration = new StepStoneConfiguration(properties);
        this.service = new WeatherServiceImpl(configuration);
    }

    @DisplayName("Test weatherService")
    @Test
    public void getWeatherFromProviders() throws URISyntaxException {
        List<StepStoneRequestDTO> requestList = new ArrayList<>();
        List<String> citiesList = new ArrayList<>();
        citiesList.add("Gdansk");
        StepStoneRequestDTO requestDTO = new StepStoneRequestDTO(WeatherProviderType.ACCU_WEATHER, citiesList);
        requestList.add(requestDTO);
        List<StepStoneResultDTO> resultList = new ArrayList<>();
        HashMap<String, String> providerResult = new HashMap<>();
        providerResult.put("Gdansk", "30");
        StepStoneResultDTO result = new StepStoneResultDTO("accuWeather", providerResult);
        resultList.add(result);
        Mockito.when(service.getWeather(requestList)).thenReturn(resultList);
        List<StepStoneResultDTO> resultStepStone = service.getWeather(Collections.emptyList());
        Assertions.assertNotNull(resultStepStone);

        for (StepStoneResultDTO dto: resultStepStone) {
            Assertions.assertEquals("accuWeather", dto.getProviderName());
            Assertions.assertEquals("30", dto.getProviderResult().get("Gdansk"));
        }


    }
}
