package com.api.ecommerce.entity.enums;

import java.util.Arrays;

import lombok.Getter;

public enum StatusEnum {

  PROCESSING("EM_PROCESSAMENTO"),
  PROCESSED("PROCESSADO"),

  PROCESSING_ERROR("ERRO");

  @Getter
  private String value;

  private StatusEnum(String value) {
    this.value = value;
  }

  public static StatusEnum findByValue(final String value) {
    return Arrays.stream(values()).filter(v -> v.getValue().equals(value)).findFirst().orElse(null);
  }
}
