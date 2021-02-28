package com.tenera.interview.weatherapp.service;

import com.tenera.interview.weatherapp.constants.ErrorConstants;
import com.tenera.interview.weatherapp.exception.ApplicationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RestTemplateService {

    private static final Logger LOGGER = LogManager.getLogger(RestTemplateService.class);

    private final RestTemplate restTemplate;

    @Autowired
    public RestTemplateService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> T getResponse(final UriComponentsBuilder builder, final Class<T> responseClass) {
        final String uri = builder.toUriString();
        ResponseEntity<T> response = null;
        try {
            response =
                    restTemplate.exchange(
                            new RequestEntity<>(HttpMethod.GET, builder.build().toUri()),
                            responseClass);
        } catch (HttpClientErrorException httpClientErrorException) {
                throw new ApplicationException(ErrorConstants.OPEN_WEATHER_API_ERROR_CODE, httpClientErrorException.getMessage());
        } catch (Exception exception) {
            throw new ApplicationException(
                    ErrorConstants.OPEN_WEATHER_API_ERROR_CODE,
                    ErrorConstants.OPEN_WEATHER_API_ERROR_MESSAGE,
                    exception);
        }
        return response.getBody();
    }
}
