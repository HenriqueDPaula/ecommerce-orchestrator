package com.api.ecommerce.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class MapperUtils {

  private static final String EMPTY = "";

  public static String convertObjectToJson(Object object) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.registerModule(new JavaTimeModule());
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      return EMPTY;
    }
  }
}
