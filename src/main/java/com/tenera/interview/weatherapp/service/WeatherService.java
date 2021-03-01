package com.tenera.interview.weatherapp.service;

import com.tenera.interview.weatherapp.constants.ErrorConstants;
import com.tenera.interview.weatherapp.entities.WeatherHistory;
import com.tenera.interview.weatherapp.exception.ApplicationException;
import com.tenera.interview.weatherapp.model.application.response.CurrentWeatherResponse;
import com.tenera.interview.weatherapp.model.application.response.HistoryWeatherResponse;
import com.tenera.interview.weatherapp.model.openweather.response.weather.OpenWeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class WeatherService {

    private final OpenWeatherService openWeatherService;
    private final Set<String> umbrellaWeathers;
    private final WeatherHistoryService weatherHistoryService;

    @Autowired
    public WeatherService(
            final OpenWeatherService openWeatherService,
            final Set<String> umbrellaWeathers,
            final WeatherHistoryService weatherHistoryService) {
        this.openWeatherService = openWeatherService;
        this.umbrellaWeathers = umbrellaWeathers;
        this.weatherHistoryService = weatherHistoryService;
    }

    /**
     * Calls the Open weather API and gets the weather information. Checks of the weather needs an
     * Umbrella or not. Persists the history in the database. returns the response in a suggested
     * format.
     *
     * @param cityName
     * @return
     */
    public CurrentWeatherResponse getCurrentWeather(final String cityName) {
        final OpenWeatherResponse weatherForCity = openWeatherService.getWeatherFromCity(cityName);
        final Boolean isUmbrellaNeeded =
                umbrellaWeathers.contains(weatherForCity.getWeather().get(0).getMain());
        weatherHistoryService.addWeatherDataByCityToRepo(weatherForCity, isUmbrellaNeeded);
        return CurrentWeatherResponse.builder()
                .temp((weatherForCity.getMain().getTemp()))
                .pressure(weatherForCity.getMain().getPressure())
                .umbrella(isUmbrellaNeeded)
                .build();
    }

    /**
     * Fires the database with the city Name. Gets the top 5 records from DB based on the time
     * stamp. Iterates the history records and calculates the avg temp and avg pressure. Returns the
     * formatted response as suggested.
     *
     * @param cityName
     * @return
     */
    public HistoryWeatherResponse getWeatherHistory(final String cityName) {
        List<WeatherHistory> weatherHistoryList =
                weatherHistoryService.getWeatherHistoryFroCity(cityName);
        if (CollectionUtils.isEmpty(weatherHistoryList)) {
            throw new ApplicationException(
                    ErrorConstants.NO_HISTORY_ERROR_CODE, ErrorConstants.NO_HISTORY_ERROR_MESSAGE);
        }
        return computeHistoryWeatherResponse(weatherHistoryList);
    }

    private HistoryWeatherResponse computeHistoryWeatherResponse(
            List<WeatherHistory> weatherHistoryList) {
        final HistoryWeatherResponse historyWeatherResponse = new HistoryWeatherResponse();
        Double avgTemp = 0.0;
        Integer avgPressure = 0;
        List<CurrentWeatherResponse> currentWeatherResponses = new ArrayList<>();
        for (WeatherHistory weatherHistory : weatherHistoryList) {
            currentWeatherResponses.add(
                    CurrentWeatherResponse.builder()
                            .umbrella(weatherHistory.getUmbrella())
                            .pressure(weatherHistory.getPressure())
                            .temp(weatherHistory.getTemp())
                            .build());
            avgTemp = avgTemp + weatherHistory.getTemp();
            avgPressure = avgPressure + weatherHistory.getPressure();
        }
        avgTemp = avgTemp / weatherHistoryList.size();
        avgPressure = avgPressure / weatherHistoryList.size();
        historyWeatherResponse.setHistory(currentWeatherResponses);
        historyWeatherResponse.setAvgTemp(avgTemp);
        historyWeatherResponse.setAvgPressure(avgPressure);
        return historyWeatherResponse;
    }
}
