package com.api.ecommerce.controller.sale.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.api.ecommerce.entity.enums.PersonTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class Customer {

  @NotBlank(message = "id não deve estar em branco")
  @NotNull(message = "id não deve ser nulo")
  @ApiModelProperty(notes = "Id", example = "123456", required = true)
  private String id;

  @NotBlank(message = "nome não deve estar em branco")
  @NotNull(message = "nome não deve ser nulo")
  @ApiModelProperty(notes = "Nome", example = "Givaldo Santos Vasconcelos", required = true)
  private String nome;

  @NotBlank(message = "documento não deve estar em branco")
  @NotNull(message = "documento não deve ser nulo")
  @ApiModelProperty(notes = "Documento", example = "70420816097", required = true)
  private String documento;

  @NotBlank(message = "tipoDocumento não deve estar em branco")
  @NotNull(message = "tipoDocumento não deve ser nulo")
  @ApiModelProperty(notes = "Tipo Documento", example = "CPF", required = true)
  private String tipoDocumento;

  @NotNull(message = "tipoPessoa não deve ser nulo")
  @ApiModelProperty(notes = "Tipo Pessoa", example = "F", required = true)
  private PersonTypeEnum tipoPessoa;

  @NotBlank(message = "endereco não deve estar em branco")
  @NotNull(message = "endereco não deve ser nulo")
  @ApiModelProperty(notes = "Endereço", example = "Travessa Francisco Vieira", required = true)
  private String endereco;

  @NotBlank(message = "numeroEndereco não deve estar em branco")
  @NotNull(message = "numeroEndereco não deve ser nulo")
  @ApiModelProperty(notes = "Número endereço", example = "11", required = true)
  private String numeroEndereco;

  @JsonProperty(value = "complementoEndereco")
  @ApiModelProperty(notes = "Complemento endereço", example = "Apto 405")
  private String complementoEndereco;

  @NotBlank(message = "bairro não deve estar em branco")
  @NotNull(message = "bairro não deve ser nulo")
  @ApiModelProperty(notes = "Bairro", example = "Trapiche da Barra", required = true)
  private String bairro;

  @NotBlank(message = "cidade não deve estar em branco")
  @NotNull(message = "cidade não deve ser nulo")
  @ApiModelProperty(notes = "Cidade", example = "Maceió", required = true)
  private String cidade;

  @NotBlank(message = "estado não deve estar em branco")
  @NotNull(message = "estado não deve ser nulo")
  @ApiModelProperty(notes = "Estado", example = "AL", required = true)
  private String estado;

  @NotBlank(message = "pais não deve estar em branco")
  @NotNull(message = "pais não deve ser nulo")
  @ApiModelProperty(notes = "Pais", example = "BR", required = true)
  private String pais;

  @NotBlank(message = "cep não deve estar em branco")
  @NotNull(message = "cep não deve ser nulo")
  @ApiModelProperty(notes = "CEP", example = "57010460", required = true)
  private String cep;

  @NotBlank(message = "codigoIbge não deve estar em branco")
  @NotNull(message = "codigoIbge não deve ser nulo")
  @ApiModelProperty(notes = "Código IBGE", example = "7162435", required = true)
  private String codigoIbge;

  @NotBlank(message = "telefone não deve estar em branco")
  @NotNull(message = "telefone não deve ser nulo")
  @ApiModelProperty(notes = "Telefone", example = "(82) 36774-7713", required = true)
  private String telefone;

  @NotBlank(message = "email não deve estar em branco")
  @NotNull(message = "email não deve ser nulo")
  @ApiModelProperty(notes = "E-mail", example = "givaldo.santos.vasconcelos@gmail.com", required = true)
  private String email;
}
