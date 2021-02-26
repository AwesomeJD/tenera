package com.tenera.interview.weatherapp.service;

import static org.junit.jupiter.api.Assertions.*;

import com.tenera.interview.weatherapp.model.openweather.response.history.HistoryWeatherResponse;
import com.tenera.interview.weatherapp.model.openweather.response.weather.Coord;
import com.tenera.interview.weatherapp.model.openweather.response.weather.OpenWeatherResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OpenWeatherServiceTest {

    @Autowired private OpenWeatherService openWeatherService;

    @Test
    public void callOpenWeatherApiSuccessForWeatherWithCityName() {
        // given
        // when
        final OpenWeatherResponse response = openWeatherService.getWeatherFromCity("Berlin");
        // then
        assertNotNull(response.getMain().getTemp());
    }

    @Test
    public void callOpenWeatherApiSuccessForWeatherWithCityNameAndCountryCode() {
        // given
        // when
        final OpenWeatherResponse response = openWeatherService.getWeatherFromCity("Berlin,de");
        // then
        assertNotNull(response.getMain().getTemp());
    }

    @Test
    public void callOpenWeatherApiSuccessForHistoryWithCoordinates() {
        // given
        // Coordinates of Berlin
        final Double lon = 13.4105;
        final Double lat = 52.5244;
        final Coord coordinate = Coord.builder().lat(lat).lon(lon).build();
        // when
        final HistoryWeatherResponse response =
                openWeatherService.getWeatherHistoryFromCoordinate(coordinate);
        // then
        assertNotNull(response.getCurrent().getTemp());
    }
}