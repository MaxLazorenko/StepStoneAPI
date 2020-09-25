package com.turingsolutions.weatherapi.service.weather.providers;

import com.turingsolutions.weatherapi.service.weather.providers.response.accuWeather.AccuWeatherCityInfo;
import com.turingsolutions.weatherapi.service.weather.providers.response.accuWeather.AccuWeatherResponse;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class AccuWeatherProvider extends DefaultWeatherProvider<AccuWeatherResponse> {
    private final RestTemplate restTemplate;

    public AccuWeatherProvider(String apiKey, String url, RestTemplate restTemplate) {
        super(apiKey, url);
        this.restTemplate = restTemplate;
    }

    @Override
    public AccuWeatherResponse getWeatherByCity(String city) throws URISyntaxException {
        String searchCityPath = getUrl() + "/locations/v1/cities/search";

        URI citySearchUrl = new URIBuilder(searchCityPath)
                                .addParameter("apikey", getApiKey())
                                .addParameter("q", city)
                                .build();

        AccuWeatherCityInfo[] cityInfo = this.restTemplate.getForObject(citySearchUrl, AccuWeatherCityInfo[].class);

        if (cityInfo != null) {
            for (AccuWeatherCityInfo info: cityInfo) {
                if (cityInfo != null) {
                    String searchTemperaturePath = getUrl() +
                                                   "/forecasts/v1/daily/1day/" +
                                                   info.getKey();

                    URI forecastSearch = new URIBuilder(searchTemperaturePath)
                        .addParameter("apikey", getApiKey()).build();
                    AccuWeatherResponse response = this.restTemplate.getForObject(forecastSearch, AccuWeatherResponse.class);

                    return response;
                }
            }
        }



        return new AccuWeatherResponse();
    }
}
