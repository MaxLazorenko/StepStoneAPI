package com.turingsolutions.weatherapi.service.weather;

import com.turingsolutions.weatherapi.config.stepstone.StepStoneConfiguration;
import com.turingsolutions.weatherapi.service.dto.StepStoneRequestDTO;
import com.turingsolutions.weatherapi.service.dto.StepStoneResultDTO;
import com.turingsolutions.weatherapi.service.weather.interfaces.WeatherProvider;
import com.turingsolutions.weatherapi.service.weather.interfaces.WeatherService;
import com.turingsolutions.weatherapi.service.weather.providers.WeatherProviderType;
import com.turingsolutions.weatherapi.service.weather.providers.response.accuWeather.AccuWeatherResponse;
import com.turingsolutions.weatherapi.service.weather.providers.response.accuWeather.DailyForecast;
import com.turingsolutions.weatherapi.service.weather.providers.response.bitWeather.BitWeatherData;
import com.turingsolutions.weatherapi.service.weather.providers.response.bitWeather.BitWeatherResponse;
import com.turingsolutions.weatherapi.service.weather.providers.response.openWeather.OpenWeatherResponse;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.*;

@Service
public class WeatherServiceImpl implements WeatherService {
    private final StepStoneConfiguration configuration;

    public WeatherServiceImpl(StepStoneConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public List<StepStoneResultDTO> getWeather(List<StepStoneRequestDTO> request) throws URISyntaxException {
        List<StepStoneResultDTO> result = new ArrayList<>();

        if (request == null) {
            return  Collections.emptyList();
        }
        for (StepStoneRequestDTO req: request) {
            switch (req.getProviderType()) {
                case ACCU_WEATHER:
                    result.add(new StepStoneResultDTO("accuWeather", getAccuWeatherResult(req)));
                    break;
                case OPEN_WEATHER:
                    result.add(new StepStoneResultDTO("openWeather", getOpenWeatherResult(req)));
                    break;
                case WEATHER_BIT:
                    result.add(new StepStoneResultDTO("bitWeather", getBitWeatherResult(req)));
                    break;
                case ALL:
                    result.add(new StepStoneResultDTO("accuWeather", getAccuWeatherResult(req.withType(WeatherProviderType.ACCU_WEATHER))));
                    result.add(new StepStoneResultDTO("bitWeather", getBitWeatherResult(req.withType(WeatherProviderType.WEATHER_BIT))));
                    result.add(new StepStoneResultDTO("openWeather", getOpenWeatherResult(req.withType(WeatherProviderType.OPEN_WEATHER))));
                    break;
            }
        }

        return result;
    }
    private Map<String, String> getAccuWeatherResult(StepStoneRequestDTO req) throws URISyntaxException {
        Map<String , String> accuWeatherResult = new HashMap<>();

        WeatherProvider<AccuWeatherResponse> weatherProvider = WeatherProviderFactory.createProviderByType(req.getProviderType(), configuration);

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
    private Map<String, String> getOpenWeatherResult(StepStoneRequestDTO req) throws URISyntaxException {
        Map<String , String> openWeatherResult = new HashMap<>();

        WeatherProvider<OpenWeatherResponse> weatherProvider = WeatherProviderFactory.createProviderByType(req.getProviderType(), configuration);

        for (String city: req.getCitiesList()) {
            OpenWeatherResponse weatherRes = weatherProvider.getWeatherByCity(city);

            String temperature = String.valueOf(weatherRes.getMain().getTemp());
            openWeatherResult.put(city, temperature);

        }
        return openWeatherResult;
    }
    private Map<String, String> getBitWeatherResult(StepStoneRequestDTO req) throws URISyntaxException {
        Map<String , String> bitWeatherResult = new HashMap<>();

        WeatherProvider<BitWeatherResponse> weatherProvider = WeatherProviderFactory.createProviderByType(req.getProviderType(), configuration);

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
