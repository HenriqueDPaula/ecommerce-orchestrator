package com.api.ecommerce.controller.sale.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.annotations.ApiModelProperty;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {

  @NotNull(message = "sku não deve ser nulo")
  @ApiModelProperty(notes = "SKU", example = "324226428", required = true)
  private Integer sku;

  @NotNull(message = "quantidade não deve ser nulo")
  @ApiModelProperty(notes = "Quantidade", example = "3", required = true)
  private Integer quantidade;

  @NotNull(message = "valor não deve ser nulo")
  @ApiModelProperty(notes = "Valor", example = "5691", required = true)
  private Integer valor;
}
