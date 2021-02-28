package com.tenera.interview.weatherapp.controller;

import com.tenera.interview.weatherapp.constants.ErrorConstants;
import com.tenera.interview.weatherapp.model.application.response.CurrentWeatherResponse;
import com.tenera.interview.weatherapp.model.application.response.HistoryWeatherResponse;
import com.tenera.interview.weatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@Validated
public class WeatherAppController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherAppController(final WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @RequestMapping(value = "/current")
    public ResponseEntity<CurrentWeatherResponse> getCurrentWeather(
            @NotBlank(message = ErrorConstants.LOCATION_CANNOT_BE_BLANK) @RequestParam("location")
                    final String location) {

        final CurrentWeatherResponse currentWeatherResponse =
                weatherService.getCurrentWeather(location);
        return ResponseEntity.ok(currentWeatherResponse);
    }

    @RequestMapping(value = "/history")
    public ResponseEntity<HistoryWeatherResponse> getWeatherHistory(
            @NotBlank(message = ErrorConstants.LOCATION_CANNOT_BE_BLANK) @RequestParam("location")
                    final String location) {

        final HistoryWeatherResponse historyWeatherResponse =
                weatherService.getWeatherHistory(location);
        return ResponseEntity.ok(historyWeatherResponse);
    }
}
