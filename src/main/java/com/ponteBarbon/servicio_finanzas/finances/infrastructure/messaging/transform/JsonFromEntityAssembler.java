package com.ponteBarbon.servicio_finanzas.finances.infrastructure.messaging.transform;

import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonFromEntityAssembler {

    public static String toJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(json, clazz);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
