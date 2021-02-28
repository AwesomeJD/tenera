package com.tenera.interview.weatherapp.model.openweather.response.history;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoryWeatherResponse {
    private Double lat;
    private Double lon;
    private String timezone;
    private Integer timezoneOffset;
    private Current current;
}
