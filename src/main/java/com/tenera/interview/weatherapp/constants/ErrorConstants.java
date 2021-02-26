package com.tenera.interview.weatherapp.constants;

public interface ErrorConstants {

    String GENERIC_ERROR_CODE = "1000";
    String GENERIC_ERROR_MESSAGE = "Error in Application, please check logs.";

    String OPEN_WEATHER_API_ERROR_CODE = "1001";
    String OPEN_WEATHER_API_ERROR_MESSAGE = "Error in the amazon api, please check logs.";

    String BAD_USER_INPUT_ERROR_CODE = "1002";

    String CITY_NAME_CANNOT_BE_BLANK = "Keyword cannot be blank.";
}
