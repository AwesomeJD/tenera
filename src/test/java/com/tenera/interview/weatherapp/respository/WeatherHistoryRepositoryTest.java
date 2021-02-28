package com.tenera.interview.weatherapp.respository;

import com.tenera.interview.weatherapp.entities.WeatherHistory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class WeatherHistoryRepositoryTest {

    @Autowired
    private WeatherHistoryRepository repository;

    @Test
    public void testPersistOfWeatherHistory(){
        //given
        final WeatherHistory weatherHistoryToSave = new WeatherHistory();
        weatherHistoryToSave.setUmbrella(true);
        //when
        final WeatherHistory weatherHistory = repository.save(weatherHistoryToSave);
        //then
        assertNotNull(weatherHistory);
        assertNotNull(weatherHistory.getId());
        assertTrue(weatherHistory.getUmbrella());
    }
}