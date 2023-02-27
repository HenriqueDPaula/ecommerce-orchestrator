package com.api.ecommerce.driver.canal;

import java.time.LocalDateTime;

import com.api.ecommerce.driver.sefaz.AuthorizationResponse;
import com.api.ecommerce.entity.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CallbackRequest {

  @JsonProperty(value = "numeroPedido")
  private String orderNumber;

  @JsonProperty(value = "numeroOrdemExterno")
  private String externalOrderNumber;

  @JsonProperty(value = "chaveNFE")
  private String nfeKey;

  @JsonProperty(value = "numeroNota")
  private String noteNumber;

  @JsonProperty(value = "dataEmissao")
  private LocalDateTime issuanceDate;

  private String pdf;

  private StatusEnum status;

  public static CallbackRequest from(AuthorizationResponse authorizationResponse, String orderNumber, String externalOrderNumber,
                                   StatusEnum statusEnum) {
    return CallbackRequest.builder()
        .orderNumber(orderNumber)
        .externalOrderNumber(externalOrderNumber)
        .nfeKey(authorizationResponse.getNfeKey())
        .noteNumber(authorizationResponse.getInvoiceNumber())
        .issuanceDate(authorizationResponse.getIssuanceDate())
        .pdf(authorizationResponse.getInvoice())
        .status(statusEnum)
        .build();
  }

}