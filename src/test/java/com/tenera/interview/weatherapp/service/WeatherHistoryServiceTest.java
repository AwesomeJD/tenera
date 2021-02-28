package com.tenera.interview.weatherapp.service;

import com.tenera.interview.weatherapp.entities.WeatherHistory;
import com.tenera.interview.weatherapp.model.openweather.response.weather.Main;
import com.tenera.interview.weatherapp.model.openweather.response.weather.OpenWeatherResponse;
import com.tenera.interview.weatherapp.model.openweather.response.weather.Sys;
import com.tenera.interview.weatherapp.respository.WeatherHistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class WeatherHistoryServiceTest {

    @MockBean private WeatherHistoryRepository repository;

    @Autowired private WeatherHistoryService weatherHistoryService;

    @Test
    public void testAddWeatherDataByCityToRepoSuccess() {
        // given
        OpenWeatherResponse response =
                OpenWeatherResponse.builder()
                        .main(Main.builder().temp(0.0).pressure(1234).build())
                        .sys(Sys.builder().country("DE").build())
                        .build();
        Mockito.when(repository.save(Mockito.any())).thenReturn(new WeatherHistory());
        // when
        weatherHistoryService.addWeatherDataByCityToRepo(response, true);
        // then
    }
}
