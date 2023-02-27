package com.api.ecommerce.driver.tributary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tribute {

  private Integer sku;
  private Double valorIcms;
  private Double valorPis;
  private Double valorDifaul;
  private Double valorFcpIcms;
}
