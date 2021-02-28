package com.tenera.interview.weatherapp.model.openweather.response.history;

import com.tenera.interview.weatherapp.model.openweather.response.weather.Weather;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Current {

    private Integer dt;
    private Double temp;
    private Integer pressure;
    private List<Weather> weather = null;
}
