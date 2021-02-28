package com.tenera.interview.weatherapp.service;

import com.tenera.interview.weatherapp.model.application.response.CurrentWeatherResponse;
import com.tenera.interview.weatherapp.model.application.response.HistoryWeatherResponse;
import com.tenera.interview.weatherapp.model.openweather.response.weather.OpenWeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class WeatherService {

    private final OpenWeatherService openWeatherService;
    private final Set<String> umbrellaWeathers;
    private final WeatherHistoryService weatherHistoryService;

    @Autowired
    public WeatherService(
            final OpenWeatherService openWeatherService,
            final Set<String> umbrellaWeathers,
            final WeatherHistoryService weatherHistoryService) {
        this.openWeatherService = openWeatherService;
        this.umbrellaWeathers = umbrellaWeathers;
        this.weatherHistoryService = weatherHistoryService;
    }

    @Cacheable("weatherInfoByCity")
    public CurrentWeatherResponse getCurrentWeather(final String cityName) {
        final OpenWeatherResponse weatherForCity = openWeatherService.getWeatherFromCity(cityName);
        final Boolean isUmbrellaNeeded =
                umbrellaWeathers.contains(weatherForCity.getWeather().get(0).getMain());
        weatherHistoryService.addWeatherDataByCityToRepo(weatherForCity, isUmbrellaNeeded);
        return CurrentWeatherResponse.builder()
                .temp((weatherForCity.getMain().getTemp()))
                .pressure(weatherForCity.getMain().getPressure())
                .umbrella(isUmbrellaNeeded)
                .build();
    }

    public HistoryWeatherResponse getWeatherHistory(final String cityName) {
        final OpenWeatherResponse weatherFromCity = openWeatherService.getWeatherFromCity(cityName);
        // final HistoryWeatherResponse historyWeatherResponse =
        return null;
    }
}
