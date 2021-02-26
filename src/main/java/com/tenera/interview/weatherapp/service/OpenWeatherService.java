package com.tenera.interview.weatherapp.service;

import com.tenera.interview.weatherapp.constants.ApplicationConstants;
import com.tenera.interview.weatherapp.constants.ErrorConstants;
import com.tenera.interview.weatherapp.exception.ApplicationException;
import com.tenera.interview.weatherapp.model.openweather.response.history.HistoryWeatherResponse;
import com.tenera.interview.weatherapp.model.openweather.response.weather.Coord;
import com.tenera.interview.weatherapp.model.openweather.response.weather.OpenWeatherResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OpenWeatherService {

    private static final Logger LOGGER = LogManager.getLogger(OpenWeatherService.class);

    @Value(value = "${service.open-weather.api-data.endpoint}")
    private String currentWeatherEndpoint;

    @Value(value = "${service.open-weather.api-history.endpoint}")
    private String historyWeatherEndpoint;

    @Value(value = "${service.open-weather.appid}")
    private String appid;

    @Value(value = "${service.open-weather.units}")
    private String units;

    @Autowired private RestTemplate restTemplate;

    public OpenWeatherResponse getWeatherFromCity(final String cityName) {

        final UriComponentsBuilder builder =
                UriComponentsBuilder.fromUriString(currentWeatherEndpoint)
                        .queryParam(ApplicationConstants.QUERY_PARAM_WEATHER_CITY, cityName)
                        .queryParam(ApplicationConstants.QUERY_PARAM_APP_ID, appid);

        final String uri = builder.toUriString();
        LOGGER.info(uri, () -> "The URI formed is {}");
        ResponseEntity<OpenWeatherResponse> response = null;
        try {
            response =
                    restTemplate.exchange(
                            new RequestEntity<>(HttpMethod.GET, builder.build().toUri()),
                            OpenWeatherResponse.class);
        } catch (Exception exception) {
            throw new ApplicationException(
                    ErrorConstants.OPEN_WEATHER_API_ERROR_CODE,
                    ErrorConstants.OPEN_WEATHER_API_ERROR_MESSAGE,
                    exception);
        }
        return response.getBody();
    }

    public HistoryWeatherResponse getWeatherHistoryFromCoordinate(final Coord coordinate) {
        final UriComponentsBuilder builder =
                UriComponentsBuilder.fromUriString(historyWeatherEndpoint)
                        .queryParam(ApplicationConstants.QUERY_PARAM_LATITUDE, coordinate.getLat())
                        .queryParam(ApplicationConstants.QUERY_PARAM_LONGITUDE, coordinate.getLon())
                        .queryParam(
                                ApplicationConstants.QUERY_PARAM_EXCLUDE,
                                ApplicationConstants.QUERY_PARAM_EXCLUDE_VALUES)
                        .queryParam(ApplicationConstants.QUERY_PARAM_UNITS, units)
                        .queryParam(ApplicationConstants.QUERY_PARAM_DATE, "1614149352")
                        .queryParam(ApplicationConstants.QUERY_PARAM_APP_ID, appid);

        final String uri = builder.toUriString();
        LOGGER.info(uri, () -> "The URI formed is {}");
        ResponseEntity<HistoryWeatherResponse> response = null;
        try {
            response =
                    restTemplate.exchange(
                            new RequestEntity<>(HttpMethod.GET, builder.build().toUri()),
                            HistoryWeatherResponse.class);
        } catch (Exception exception) {
            throw new ApplicationException(
                    ErrorConstants.OPEN_WEATHER_API_ERROR_CODE,
                    ErrorConstants.OPEN_WEATHER_API_ERROR_MESSAGE,
                    exception);
        }
        return response.getBody();
    }
}
