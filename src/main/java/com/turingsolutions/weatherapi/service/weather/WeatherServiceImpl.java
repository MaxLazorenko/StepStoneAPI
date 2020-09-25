package com.turingsolutions.weatherapi.service.weather;

import com.turingsolutions.weatherapi.service.dto.StepStoneRequest;
import com.turingsolutions.weatherapi.service.dto.StepStoneResult;
import com.turingsolutions.weatherapi.service.weather.interfaces.WeatherService;
import com.turingsolutions.weatherapi.service.weather.providers.AccuWeatherProvider;
import com.turingsolutions.weatherapi.service.weather.providers.OpenWeatherProvider;
import com.turingsolutions.weatherapi.service.weather.providers.response.accuWeather.AccuWeatherResponse;
import com.turingsolutions.weatherapi.service.weather.providers.response.accuWeather.DailyForecast;
import com.turingsolutions.weatherapi.service.weather.providers.response.openWeather.OpenWeatherResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.*;

@Service
public class WeatherServiceImpl implements WeatherService {
    private static final String ACCUWEATHER_KEY = "hKHpptfitzDYGTQaVIpjTD8YAwoVOt1D";
    private static final String ACCUWEATHER_URL = "http://dataservice.accuweather.com";
    private static final String OPENWEATHER_KEY = "c7aa6f8d844fe9c0790d35021b5f89f1";
    private static final String OPENWEATHER_URL = "http://api.openweathermap.org/data/2.5/weather";

    @Override
    public List<StepStoneResult> getWeather(List<StepStoneRequest> request) throws URISyntaxException {
        List<StepStoneResult> result = new ArrayList<>();

        if (request == null) {
            return  new ArrayList<>();
        }
        for (StepStoneRequest req: request) {
            switch (req.getProviderType()) {
                case ACCU_WEATHER:
                    result.add(new StepStoneResult("accuWeather", getAccuWeatherResult(req)));
                    break;
                case OPEN_WEATHER:
                    result.add(new StepStoneResult("openWeather", getOpenWeatherResult(req)));
                    break;
            }
        }

        return result;
    }
    private Map<String, String> getAccuWeatherResult(StepStoneRequest req) throws URISyntaxException {
        Map<String , String> accuWeatherResult = new HashMap<>();

        AccuWeatherProvider weatherProvider = new AccuWeatherProvider(ACCUWEATHER_KEY,
                                                                      ACCUWEATHER_URL,
                                                                      new RestTemplate());
        for (String city: req.getCitiesList()) {
            AccuWeatherResponse weatherRes = weatherProvider.getWeatherByCity(city);

            for (DailyForecast forecast: weatherRes.getDailyForecast()) {
                String temperature = String.valueOf(forecast.getTemperature().getMaximum().getValue());
                String temperatureUnit = forecast.getTemperature().getMaximum().getUnit();

                accuWeatherResult.put(city, temperature + temperatureUnit);
            }

        }

        return accuWeatherResult;
    }
    private Map<String, String> getOpenWeatherResult(StepStoneRequest req) throws URISyntaxException {
        Map<String , String> openWeatherResult = new HashMap<>();

        OpenWeatherProvider weatherProvider = new OpenWeatherProvider(OPENWEATHER_KEY,
                                                                      OPENWEATHER_URL,
                                                                      new RestTemplate());

        for (String city: req.getCitiesList()) {
            OpenWeatherResponse weatherRes = weatherProvider.getWeatherByCity(city);

            String temperature = String.valueOf(weatherRes.getMain().getTemp());
            openWeatherResult.put(city, temperature);

        }
        return openWeatherResult;
    }
}
