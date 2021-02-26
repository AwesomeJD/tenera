package com.tenera.interview.weatherapp.model.openweather.response.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Main {

    private Double temp;
    private Integer pressure;
}
