package com.tenera.interview.weatherapp.service;

import com.tenera.interview.weatherapp.model.application.response.CurrentWeatherResponse;
import com.tenera.interview.weatherapp.model.openweather.response.weather.OpenWeatherResponse;
import com.tenera.interview.weatherapp.util.AppUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class WeatherServiceTest {

    @MockBean private OpenWeatherService openWeatherService;

    @Autowired private WeatherService weatherService;

    @Test
    public void testGetCurrentWeatherSuccess() {
        // given
        final String cityName = "Berlin,DE";
        final String openWeatherResponse =
                "{\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\"}],\"main\":{\"temp\":3.58,\"pressure\":1035},\"dt\":1614568546,\"sys\":{\"country\":\"DE\"},\"name\":\"Berlin\"}";

        Mockito.when(openWeatherService.getWeatherFromCity(cityName))
                .thenReturn(AppUtils.objectify(openWeatherResponse, OpenWeatherResponse.class));
        // when
        final CurrentWeatherResponse currentWeather = weatherService.getCurrentWeather(cityName);
        // then
        assertNotNull(currentWeather);
        assertNotNull(currentWeather.getTemp());
    }
}
