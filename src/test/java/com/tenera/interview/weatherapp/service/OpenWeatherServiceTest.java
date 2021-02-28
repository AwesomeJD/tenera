package com.tenera.interview.weatherapp.service;

import com.tenera.interview.weatherapp.model.openweather.response.weather.OpenWeatherResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}
