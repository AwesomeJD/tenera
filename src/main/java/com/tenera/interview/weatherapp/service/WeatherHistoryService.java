package com.tenera.interview.weatherapp.service;

import com.tenera.interview.weatherapp.constants.ErrorConstants;
import com.tenera.interview.weatherapp.entities.WeatherHistory;
import com.tenera.interview.weatherapp.exception.ApplicationException;
import com.tenera.interview.weatherapp.model.openweather.response.weather.OpenWeatherResponse;
import com.tenera.interview.weatherapp.respository.WeatherHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class WeatherHistoryService {

    private final WeatherHistoryRepository repository;

    @Autowired
    public WeatherHistoryService(final WeatherHistoryRepository repository) {
        this.repository = repository;
    }

    public void addWeatherDataByCityToRepo(
            final OpenWeatherResponse weatherForCity, final Boolean isUmbrellaNeeded) {
        final Long currentUnixTime =
                LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
        WeatherHistory weatherHistory = new WeatherHistory();
        weatherHistory.setUnixTimeDate(currentUnixTime);
        weatherHistory.setPressure(weatherForCity.getMain().getPressure());
        weatherHistory.setTemp(weatherForCity.getMain().getTemp());
        weatherHistory.setCity(weatherForCity.getName());
        weatherHistory.setCountryCode(weatherForCity.getSys().getCountry());
        weatherHistory.setUmbrella(isUmbrellaNeeded);
        try {
            repository.save(weatherHistory);
        } catch (Exception exception) {
            throw new ApplicationException(
                    ErrorConstants.DB_ERROR_CODE, ErrorConstants.DB_ERROR_MESSAGE, exception);
        }
    }


    public List<WeatherHistory> getWeatherHistoryFroCity(final String cityName) {
        List<WeatherHistory> weatherHistoryList = null;
        try {
            if (cityName.contains(",")) {
                final String[] cityAndCountryCode = cityName.split(",");
                weatherHistoryList =
                        repository.findTop5ByCityAndCountryCodeOrderByUnixTimeDateDesc(
                                cityAndCountryCode[0], cityAndCountryCode[1]);
            } else {
                weatherHistoryList = repository.findTop5ByCityOrderByUnixTimeDateDesc(cityName);
            }
        } catch (Exception exception) {
            throw new ApplicationException(
                    ErrorConstants.DB_ERROR_CODE, ErrorConstants.DB_ERROR_MESSAGE, exception);
        }
        return weatherHistoryList;
    }
}
