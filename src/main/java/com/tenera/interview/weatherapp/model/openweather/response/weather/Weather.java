package com.tenera.interview.weatherapp.model.openweather.response.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

    private Integer id;
    private String main;
    private String description;
}
