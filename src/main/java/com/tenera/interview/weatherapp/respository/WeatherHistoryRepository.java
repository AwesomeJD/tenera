package com.tenera.interview.weatherapp.respository;

import com.tenera.interview.weatherapp.entities.WeatherHistory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WeatherHistoryRepository extends CrudRepository<WeatherHistory, Long> {

    public List<WeatherHistory> findTop5ByCityOrderByUnixTimeDateDesc(final String city);

    public List<WeatherHistory> findTop5ByCityAndCountryCodeOrderByUnixTimeDateDesc(
            final String city, final String countryCode);
}
