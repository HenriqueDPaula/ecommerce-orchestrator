package com.api.ecommerce.controller.sale.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
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
public class Order {

  @NotBlank(message = "numeroPedido não deve estar em branco")
  @NotNull(message = "numeroPedido não deve ser nulo")
  @ApiModelProperty(notes = "Número do pedido", example = "101628208632", required = true)
  private String numeroPedido;

  @NotBlank(message = "numeroOrdemExterno não deve estar em branco")
  @NotNull(message = "numeroOrdemExterno não deve ser nulo")
  @ApiModelProperty(notes = "Número ordem externo", example = "100423672693-1", required = true)
  private String numeroOrdemExterno;

  @NotNull(message = "dataAutorizacao não deve ser nulo")
  @ApiModelProperty(notes = "Data da autorização", example = "2022-11-11T15:37:56.194", required = true)
  private LocalDateTime dataAutorizacao;
}
