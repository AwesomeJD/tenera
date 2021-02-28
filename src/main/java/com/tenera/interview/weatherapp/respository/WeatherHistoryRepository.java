package com.tenera.interview.weatherapp.respository;

import com.tenera.interview.weatherapp.entities.WeatherHistory;
import org.springframework.data.repository.CrudRepository;

public interface WeatherHistoryRepository extends CrudRepository<WeatherHistory, Long> {}
