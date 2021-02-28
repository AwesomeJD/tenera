package com.tenera.interview.weatherapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@ConfigurationProperties("service.umbrella.weathers")
public class UmbrellaWeather {

    private Set<String> umbrellaWeathers;

    public Set<String> getUmbrellaWeathers() {
        return umbrellaWeathers;
    }

    public void setUmbrellaWeathers(Set<String> umbrellaWeathers) {
        this.umbrellaWeathers = umbrellaWeathers;
    }
}
