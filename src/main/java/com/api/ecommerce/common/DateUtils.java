package com.api.ecommerce.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DateUtils {

  public static String retrieveCurrentDateString() {
    return LocalDateTime.now().toString();
  }

  public static LocalDateTime retrieveDateFromString(String dateString) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
    return Optional.of(dateString)
        .map(date -> LocalDateTime.parse(date, formatter))
        .orElse(LocalDateTime.now());
  }
}
