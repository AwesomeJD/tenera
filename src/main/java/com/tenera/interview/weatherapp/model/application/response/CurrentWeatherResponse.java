package com.tenera.interview.weatherapp.model.application.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CurrentWeatherResponse {
    private Double temp;
    private Integer pressure;
    private Boolean umbrella;
}
