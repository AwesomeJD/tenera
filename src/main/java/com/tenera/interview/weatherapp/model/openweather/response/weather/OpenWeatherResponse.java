package com.tenera.interview.weatherapp.model.openweather.response.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpenWeatherResponse {
    private Coord coord;
    private List<Weather> weather = null;
    private Main main;
    private Integer dt;
    private Sys sys;
    private String name;
}
