package com.tenera.interview.weatherapp.model.application.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoryWeatherResponse {
    private Integer avgTemp;
    private Integer avgPressure;
    private List<CurrentWeatherResponse> history = null;
}
