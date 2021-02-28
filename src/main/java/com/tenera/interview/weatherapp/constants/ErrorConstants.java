package com.tenera.interview.weatherapp.constants;

public interface ErrorConstants {

    String GENERIC_ERROR_CODE = "1000";
    String GENERIC_ERROR_MESSAGE = "Error in Application, please check logs.";

    String OPEN_WEATHER_API_ERROR_CODE = "1001";
    String OPEN_WEATHER_API_ERROR_MESSAGE = "Error in the amazon api, please check logs.";

    String BAD_USER_INPUT_ERROR_CODE = "1002";

    String DB_ERROR_CODE = "1003";
    String DB_ERROR_MESSAGE = "Error in DB operation, please check logs.";

    String NO_HISTORY_ERROR_CODE = "1004";
    String NO_HISTORY_ERROR_MESSAGE = "No weather history for the location provided";

    String LOCATION_CANNOT_BE_BLANK = "Location cannot be blank.";
}
