package com.api.ecommerce.controller.sale.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleResponse {

  @JsonProperty("status")
  private String status;

  @JsonProperty("dataResposta")
  private String responseDate;
}
