package com.tenera.interview.weatherapp.cache;

import com.tenera.interview.weatherapp.model.openweather.response.weather.OpenWeatherResponse;
import com.tenera.interview.weatherapp.service.OpenWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherCacheService {

    private final OpenWeatherService openWeatherService;

    @Autowired
    public WeatherCacheService(final OpenWeatherService openWeatherService) {
        this.openWeatherService = openWeatherService;
    }

    //   @Cacheable("weatherInfoByCity")
    public OpenWeatherResponse getWeatherFromCity(final String cityName) {
        return openWeatherService.getWeatherFromCity(cityName);
    }
}
