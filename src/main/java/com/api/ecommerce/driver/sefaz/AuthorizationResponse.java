package com.api.ecommerce.driver.sefaz;

import java.time.LocalDateTime;

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
public class AuthorizationResponse {

  private String nfeKey;

  private String invoiceNumber;

  private LocalDateTime issuanceDate;

  private String invoice;
}
