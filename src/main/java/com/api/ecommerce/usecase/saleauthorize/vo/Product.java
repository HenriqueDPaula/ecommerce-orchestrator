package com.api.ecommerce.usecase.saleauthorize.vo;

import com.api.ecommerce.driver.tributary.Tribute;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

  private Integer sku;
  private Integer amount;
  private Double value;

  private Double icmsValue;

  private Double pisValue;

  private Double difaulValue;

  private Double fcpIcmsValue;

  public static Product of(Tribute tribute, Integer amount, Double value) {
    return builder()
        .sku(tribute.getSku())
        .amount(amount)
        .value(value)
        .icmsValue(tribute.getValorIcms())
        .pisValue(tribute.getValorPis())
        .difaulValue(tribute.getValorDifaul())
        .fcpIcmsValue(tribute.getValorFcpIcms())
        .build();
  }
}
