package com.tenera.interview.weatherapp.service;

import com.tenera.interview.weatherapp.constants.ApplicationConstants;
import com.tenera.interview.weatherapp.model.openweather.response.weather.OpenWeatherResponse;
import com.tenera.interview.weatherapp.util.AppUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Base64;

@Service
public class OpenWeatherService {

    private static final Logger LOGGER = LogManager.getLogger(OpenWeatherService.class);

    @Value(value = "${service.open-weather.api-data.endpoint}")
    private String currentWeatherEndpoint;

    @Value(value = "${service.open-weather.appid}")
    private String appid;

    @Value(value = "${service.open-weather.units}")
    private String units;

    private final RestTemplateService restTemplateService;

    @Autowired
    public OpenWeatherService(final RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    public OpenWeatherResponse getWeatherFromCity(final String cityName) {
        LOGGER.info("Calling the weather API fro the city, {}", cityName);
        final UriComponentsBuilder builder =
                UriComponentsBuilder.fromUriString(currentWeatherEndpoint)
                        .queryParam(ApplicationConstants.QUERY_PARAM_WEATHER_CITY, cityName)
                        .queryParam(ApplicationConstants.QUERY_PARAM_UNITS, units)
                        .queryParam(
                                ApplicationConstants.QUERY_PARAM_APP_ID,
                                new String(Base64.getDecoder().decode(appid)));
        final OpenWeatherResponse response =
                restTemplateService.getResponse(builder, OpenWeatherResponse.class);

        LOGGER.info("Response received from Open weather service {}", AppUtils.stringify(response));
        return response;
    }
}
