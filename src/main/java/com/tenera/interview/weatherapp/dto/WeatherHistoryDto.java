package com.tenera.interview.weatherapp.dto;

import com.tenera.interview.weatherapp.model.application.response.CurrentWeatherResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherHistoryDto extends CurrentWeatherResponse {
    private String city;
}
