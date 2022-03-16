package com.senla.javaee.controller.mapper;


import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;


import org.springframework.stereotype.Component;

@Component
public class JsonMapper {


    private final ObjectMapper objectMapper = new ObjectMapper();

    public Object createObj(String json, Class clazz) throws JsonProcessingException {
        return objectMapper.readValue(json, clazz);

    }


    public String createJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return "json not created";
        }

    }
}

