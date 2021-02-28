package com.tenera.interview.weatherapp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AppUtils {

    public static <T> String stringify(T object) {
        final ObjectMapper objectMapper = new ObjectMapper();
        String objectAsString = null;
        try {
            objectAsString = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return objectAsString;
    }

    public static <T> T objectify(final String objectAsString, Class<T> clazz) {
        final ObjectMapper objectMapper = new ObjectMapper();
        T object = null;
        try {
            object = objectMapper.readValue(objectAsString, clazz);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return object;
    }
}
