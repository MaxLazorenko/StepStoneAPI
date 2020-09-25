package com.turingsolutions.weatherapi.service.weather;

import com.turingsolutions.weatherapi.config.stepstone.StepStoneConfiguration;
import com.turingsolutions.weatherapi.service.dto.StepStoneRequest;
import com.turingsolutions.weatherapi.service.dto.StepStoneResult;
import com.turingsolutions.weatherapi.service.weather.interfaces.WeatherService;
import com.turingsolutions.weatherapi.service.weather.providers.AccuWeatherProvider;
import com.turingsolutions.weatherapi.service.weather.providers.BitWeatherProvider;
import com.turingsolutions.weatherapi.service.weather.providers.OpenWeatherProvider;
import com.turingsolutions.weatherapi.service.weather.providers.response.accuWeather.AccuWeatherResponse;
import com.turingsolutions.weatherapi.service.weather.providers.response.accuWeather.DailyForecast;
import com.turingsolutions.weatherapi.service.weather.providers.response.bitWeather.BitWeatherData;
import com.turingsolutions.weatherapi.service.weather.providers.response.bitWeather.BitWeatherResponse;
import com.turingsolutions.weatherapi.service.weather.providers.response.openWeather.OpenWeatherResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.*;

@Service
public class WeatherServiceImpl implements WeatherService {
    private final StepStoneConfiguration configuration;

    public WeatherServiceImpl(StepStoneConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public List<StepStoneResult> getWeather(List<StepStoneRequest> request) throws URISyntaxException {
        List<StepStoneResult> result = new ArrayList<>();

        if (request == null) {
            return  new ArrayList<>();
        }
        for (StepStoneRequest req: request) {
            switch (req.getProviderType()) {
                case ALL:
                case ACCU_WEATHER:
                    result.add(new StepStoneResult("accuWeather", getAccuWeatherResult(req)));
                    break;
                case OPEN_WEATHER:
                    result.add(new StepStoneResult("openWeather", getOpenWeatherResult(req)));
                    break;
                case WEATHER_BIT:
                    result.add(new StepStoneResult("bitWeather", getBitWeatherResult(req)));
                    break;
            }
        }

        return result;
    }
    private Map<String, String> getAccuWeatherResult(StepStoneRequest req) throws URISyntaxException {
        Map<String , String> accuWeatherResult = new HashMap<>();

        AccuWeatherProvider weatherProvider = new AccuWeatherProvider(configuration.getAccuWeatherKey(),
                                                                      configuration.getAccuWeatherUrl(),
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

        OpenWeatherProvider weatherProvider = new OpenWeatherProvider(configuration.getOpenWeatherKey(),
                                                                      configuration.getOpenWeatherUrl(),
                                                                      new RestTemplate());

        for (String city: req.getCitiesList()) {
            OpenWeatherResponse weatherRes = weatherProvider.getWeatherByCity(city);

            String temperature = String.valueOf(weatherRes.getMain().getTemp());
            openWeatherResult.put(city, temperature);

        }
        return openWeatherResult;
    }
    private Map<String, String> getBitWeatherResult(StepStoneRequest req) throws URISyntaxException {
        Map<String , String> bitWeatherResult = new HashMap<>();

        BitWeatherProvider weatherProvider = new BitWeatherProvider(configuration.getBitWeatherKey(),
                                                                    configuration.getBitWeatherUrl(),
                                                                      new RestTemplate());

        for (String city: req.getCitiesList()) {
            BitWeatherResponse weatherRes = weatherProvider.getWeatherByCity(city);

            for (BitWeatherData data: weatherRes.getData()) {
                String temperature = String.valueOf(data.getTemp());
                bitWeatherResult.put(city, temperature);
            }

        }

        return bitWeatherResult;
    }
}
