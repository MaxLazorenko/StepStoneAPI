package com.turingsolutions.weatherapi.service.weather.providers;

import com.turingsolutions.weatherapi.service.weather.providers.response.bitWeather.BitWeatherResponse;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class BitWeatherProvider extends DefaultWeatherProvider<BitWeatherResponse> {

    public BitWeatherProvider(String apiKey, String url, RestTemplate template) {
        super(apiKey, url, template);
    }

    @Override
    public BitWeatherResponse getWeatherByCity(String city) throws URISyntaxException {
        URI citySearchUrl = new URIBuilder(getUrl())
                           .addParameter("city", city)
                           .addParameter("key", getApiKey())
                           .build();

        BitWeatherResponse response = getRestTemplate().getForObject(citySearchUrl, BitWeatherResponse.class);

        if (response == null) {
            return new BitWeatherResponse();
        }

        return response;
    }
}
