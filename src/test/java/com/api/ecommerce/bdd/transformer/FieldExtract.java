package com.api.ecommerce.bdd.transformer;

import java.util.Map;
import java.util.Optional;

import com.api.ecommerce.entity.enums.PersonTypeEnum;

public class FieldExtract {

  protected static String extractStringFieldFromRow(Map<String, String> row, String fieldName) {
    return row.get(fieldName);
  }

  protected static Integer extractIntFieldFromRow(Map<String, String> row, String fieldName) {
    return Optional.ofNullable(row)
        .map(tableRow -> extractStringFieldFromRow(tableRow, fieldName))
        .map(Integer::valueOf)
        .orElse(null);
  }

  protected static PersonTypeEnum extractPersonTypeFromRow(Map<String, String> row, String fieldName) {
    return Optional.ofNullable(row)
        .map(tableRow -> extractStringFieldFromRow(tableRow, fieldName))
        .map(PersonTypeEnum::valueOf)
        .orElse(null);
  }

  protected static  Double extractDoubleFromRow(Map<String, String> row, String fieldName) {
    return Optional.of(row)
        .map(tableRow -> extractStringFieldFromRow(tableRow, fieldName))
        .map(Double::valueOf)
        .orElse(null);
    }

  protected static  Long extractLongFromRow(Map<String, String> row, String fieldName) {
    return Optional.of(row)
        .map(tableRow -> extractStringFieldFromRow(tableRow, fieldName))
        .map(Long::valueOf)
        .orElse(null);
  }
}
