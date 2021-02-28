package com.tenera.interview.weatherapp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class WeatherHistory {
    @Id @GeneratedValue private Long id;
    private String city;
    private String countryCode;
    private Double temp;
    private Integer pressure;
    private Boolean umbrella;
    private Long unixTimeDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Boolean getUmbrella() {
        return umbrella;
    }

    public void setUmbrella(Boolean umbrella) {
        this.umbrella = umbrella;
    }

    public Long getUnixTimeDate() {
        return unixTimeDate;
    }

    public void setUnixTimeDate(Long unixTimeDate) {
        this.unixTimeDate = unixTimeDate;
    }
}
