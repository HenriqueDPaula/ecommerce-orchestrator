package com.api.ecommerce.controller.sale.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
public class SaleRequest {

  @NotBlank(message = "canal não deve estar em branco")
  @NotNull(message = "canal não deve ser nulo")
  @ApiModelProperty(notes = "Canal", example = "APP", required = true)
  private String canal;

  @NotBlank(message = "empresa não deve estar em branco")
  @NotNull(message = "empresa não deve ser nulo")
  @ApiModelProperty(notes = "Empresa", example = "00001", required = true)
  private String empresa;

  @NotBlank(message = "loja não deve estar em branco")
  @NotNull(message = "loja não deve ser nulo")
  @ApiModelProperty(notes = "Loja", example = "00001", required = true)
  private String loja;

  @NotNull(message = "pdv não deve ser nulo")
  @ApiModelProperty(notes = "Pdv", example = "501", required = true)
  private Integer pdv;

  @Valid
  @NotNull(message = "ordemPedido não deve ser nulo")
  @ApiModelProperty(notes = "Ordem Pedido", required = true)
  private Order ordemPedido;

  @Valid
  @NotNull(message = "cliente não deve ser nulo")
  @ApiModelProperty(notes = "Cliente", required = true)
  private Customer cliente;

  @NotNull(message = "totalItens não deve ser nulo")
  @ApiModelProperty(notes = "Total de itens", example = "38744", required = true)
  private Integer totalItens;

  @NotNull(message = "quantidadeItens não deve ser nulo")
  @ApiModelProperty(notes = "Quantidade de itens", example = "6", required = true)
  private Integer quantidadeItens;

  @Valid
  @NotEmpty(message = "itens não deve estar vazio")
  @NotNull(message = "itens não deve ser nulo")
  @ApiModelProperty(notes = "Itens", required = true)
  private List<Item> itens;

}
