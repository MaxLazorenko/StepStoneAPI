package com.turingsolutions.weatherapi.service.weather.providers;

import com.turingsolutions.weatherapi.service.weather.providers.response.openWeather.OpenWeatherResponse;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class OpenWeatherProvider extends DefaultWeatherProvider<OpenWeatherResponse> {
    private final RestTemplate restTemplate;

    public OpenWeatherProvider(String apiKey, String url, RestTemplate template) {
        super(apiKey, url);
        this.restTemplate = template;
    }

    @Override
    public OpenWeatherResponse getWeatherByCity(String city) throws URISyntaxException {
        URI citySearchUrl = new URIBuilder(getUrl())
                                .addParameter("q", city)
                                .addParameter("appid", getApiKey())
                                .build();

        OpenWeatherResponse result = this.restTemplate.getForObject(citySearchUrl, OpenWeatherResponse.class);

        if (result == null) {
            return  new OpenWeatherResponse();
        }

        return result;
    }
}
