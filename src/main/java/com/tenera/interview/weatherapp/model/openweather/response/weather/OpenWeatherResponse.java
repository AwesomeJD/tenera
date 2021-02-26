package com.tenera.interview.weatherapp.model.openweather.response.weather;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
