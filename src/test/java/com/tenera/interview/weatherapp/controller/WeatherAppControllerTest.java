package com.tenera.interview.weatherapp.controller;

import com.tenera.interview.weatherapp.model.application.response.CurrentWeatherResponse;
import com.tenera.interview.weatherapp.model.application.response.HistoryWeatherResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeatherAppControllerTest {

    @Autowired private TestRestTemplate restTemplate;

    @Test
    public void testGetCurrentWeatherForALocation() {
        final CurrentWeatherResponse response =
                this.restTemplate.getForObject(
                        "/current?location=Delhi", CurrentWeatherResponse.class);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getTemp());
    }

    @Test
    public void testGetHistoryForALocation() {
        int fireCurrentWeather = 3;
        for (int i = 1; i <= fireCurrentWeather; i++) {
            this.restTemplate.getForObject("/current?location=Delhi", CurrentWeatherResponse.class);
        }
        final HistoryWeatherResponse response =
                this.restTemplate.getForObject(
                        "/history?location=Delhi", HistoryWeatherResponse.class);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getAvgTemp());
        Assertions.assertEquals(response.getHistory().size(), fireCurrentWeather);
    }
}
