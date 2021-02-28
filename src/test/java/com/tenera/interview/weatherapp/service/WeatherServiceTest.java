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
                "{\n"
                        + "   \"coord\":{\n"
                        + "      \"lon\":13.4105,\n"
                        + "      \"lat\":52.5244\n"
                        + "   },\n"
                        + "   \"weather\":[\n"
                        + "      {\n"
                        + "         \"id\":802,\n"
                        + "         \"main\":\"Clouds\",\n"
                        + "         \"description\":\"scattered clouds\"\n"
                        + "      }\n"
                        + "   ],\n"
                        + "   \"main\":{\n"
                        + "      \"temp\":274.7,\n"
                        + "      \"pressure\":1037\n"
                        + "   },\n"
                        + "   \"dt\":1614486083,\n"
                        + "   \"sys\":{\n"
                        + "      \"country\":\"DE\"\n"
                        + "   },\n"
                        + "   \"name\":\"Berlin\"\n"
                        + "}\n";

        Mockito.when(openWeatherService.getWeatherFromCity(cityName))
                .thenReturn(AppUtils.objectify(openWeatherResponse, OpenWeatherResponse.class));
        // when
        final CurrentWeatherResponse currentWeather = weatherService.getCurrentWeather(cityName);
        // then
        assertNotNull(currentWeather);
        assertNotNull(currentWeather.getTemp());
    }
}
