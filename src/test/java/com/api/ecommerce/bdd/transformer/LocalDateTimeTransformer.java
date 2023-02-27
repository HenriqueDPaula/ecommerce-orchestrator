package com.api.ecommerce.bdd.transformer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

import io.cucumber.java.DataTableType;

public class LocalDateTimeTransformer {

  @DataTableType
  public LocalDateTime transform(Map<String, String> row) {
    return LocalDateTime.of(convertStringToInt(row.get("Ano")),
        convertStringToInt(row.get("Mes")),
        convertStringToInt(row.get("Dia")),
        convertStringToInt(row.get("Hora")),
        convertStringToInt(row.get("Minuto")),
        convertStringToInt(row.get("Segundo")));
  }

  protected static LocalDateTime transformWithMili(Map<String, String> row, String fieldName) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
    return Optional.ofNullable(row)
        .map(tableRow -> tableRow.get(fieldName))
        .map(stringDate -> LocalDateTime.parse(stringDate, formatter))
        .orElse(null);
  }

  protected static LocalDateTime transform(Map<String, String> row, String fieldName) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    return Optional.ofNullable(row)
        .map(tableRow -> tableRow.get(fieldName))
        .map(stringDate -> LocalDateTime.parse(stringDate, formatter))
        .orElse(null);
  }

  private int convertStringToInt(String string) {
    return Integer.parseInt(string);
  }
}
