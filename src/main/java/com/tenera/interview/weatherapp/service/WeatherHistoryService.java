package com.tenera.interview.weatherapp.service;

import com.tenera.interview.weatherapp.entities.WeatherHistory;
import com.tenera.interview.weatherapp.model.openweather.response.weather.OpenWeatherResponse;
import com.tenera.interview.weatherapp.respository.WeatherHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class WeatherHistoryService {

    private final WeatherHistoryRepository repository;

    @Autowired
    public WeatherHistoryService(final WeatherHistoryRepository repository) {
        this.repository = repository;
    }

    public void addWeatherDataByCityToRepo(
            final OpenWeatherResponse weatherForCity, final Boolean isUmbrellaNeeded) {
        final Long currentUnixTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
        WeatherHistory weatherHistory = new WeatherHistory();
        weatherHistory.setUnixTimeDate(currentUnixTime);
        weatherHistory.setPressure(weatherForCity.getMain().getPressure());
        weatherHistory.setTemp(weatherForCity.getMain().getTemp());
        weatherHistory.setCity(weatherForCity.getName());
        weatherHistory.setCountryCode(weatherForCity.getSys().getCountry());
        weatherHistory.setUmbrella(isUmbrellaNeeded);
        repository.save(weatherHistory);
    }
}
