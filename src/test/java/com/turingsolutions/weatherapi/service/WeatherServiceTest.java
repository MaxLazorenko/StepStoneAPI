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
import com.turingsolutions.weatherapi.service.weather.providers.response.openWeather.OpenWeatherMain;
import com.turingsolutions.weatherapi.service.weather.providers.response.openWeather.OpenWeatherResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ExtendWith(MockitoExtension.class)
@RestClientTest(AccuWeatherProvider.class)
public class WeatherServiceTest {
    @Mock
    private WeatherServiceImpl service;
    private ObjectMapper objectMapper;
    private MockRestServiceServer mockRestServiceServer;



    @BeforeEach
    void SetUp() {
        this.objectMapper = new ObjectMapper();
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        StepStoneProperties properties = new StepStoneProperties();
        properties.setAccuWeather("hKHpptfitzDYGTQaVIpjTD8YAwoVOt1D;http://dataservice.accuweather.com");
        properties.setOpenWeather("c7aa6f8d844fe9c0790d35021b5f89f1;http://api.openweathermap.org/data/2.5/weather");
        properties.setWeatherBit("161b00ebc51c4d2ab7ffac3000b2bf66;http://api.weatherbit.io/v2.0/current");
        StepStoneConfiguration configuration = new StepStoneConfiguration(properties);
        this.service = new WeatherServiceImpl(configuration, restTemplateBuilder);
        this.mockRestServiceServer = MockRestServiceServer.bindTo(service.getTemplate()).build();

    }

    @DisplayName("Test weatherService")
    @Test
    public void getWeatherFromProviders() throws URISyntaxException, JsonProcessingException {
        OpenWeatherMain main = new OpenWeatherMain(30);
        String json = this.objectMapper
                      .writeValueAsString(new OpenWeatherResponse(main));
        mockRestServiceServer
            .expect(requestTo("http://api.openweathermap.org/data/2.5/weather?q=Gdansk&appid=c7aa6f8d844fe9c0790d35021b5f89f1"))
            .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

        List<StepStoneRequestDTO> requestList = new ArrayList<>();
        List<String> citiesList = new ArrayList<>();
        citiesList.add("Gdansk");
        StepStoneRequestDTO requestDTO = new StepStoneRequestDTO(WeatherProviderType.OPEN_WEATHER, citiesList);
        requestList.add(requestDTO);
        List<StepStoneResultDTO> resultList = new ArrayList<>();
        HashMap<String, String> providerResult = new HashMap<>();
        providerResult.put("Gdansk", "30");
        StepStoneResultDTO result = new StepStoneResultDTO("OPEN_WEATHER", providerResult);
        resultList.add(result);
        Mockito.lenient().when(service.getWeather(requestList)).thenReturn(resultList);
        List<StepStoneResultDTO> resultStepStone = service.getWeather(Collections.emptyList());
        Assertions.assertNotNull(resultStepStone);

        for (StepStoneResultDTO dto: resultStepStone) {
            Assertions.assertEquals("openWeather", dto.getProviderName());
            Assertions.assertEquals("30", dto.getProviderResult().get("Gdansk"));
        }


    }
}
