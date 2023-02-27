package com.api.ecommerce.usecase.saleauthorize.vo;

import com.api.ecommerce.controller.sale.dto.SaleRequest;
import com.api.ecommerce.entity.enums.PersonTypeEnum;

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
public class Customer {

  private String id;
  private String name;

  private String document;

  private String documentType;

  private PersonTypeEnum personType;

  private String address;

  private String addressNumber;

  private String addressComplement;

  private String district;

  private String city;

  private String state;

  private String country;

  private String zipCode;

  private String ibgeCode;

  private String phoneNumber;

  private String email;

  public static Customer of(SaleRequest sale) {
    return builder()
        .id(sale.getCliente().getId())
        .name(sale.getCliente().getNome())
        .document(sale.getCliente().getDocumento())
        .documentType(sale.getCliente().getTipoDocumento())
        .personType(sale.getCliente().getTipoPessoa())
        .address(sale.getCliente().getEndereco())
        .addressNumber(sale.getCliente().getNumeroEndereco())
        .addressComplement(sale.getCliente().getComplementoEndereco())
        .district(sale.getCliente().getBairro())
        .city(sale.getCliente().getCidade())
        .state(sale.getCliente().getEstado())
        .country(sale.getCliente().getPais())
        .zipCode(sale.getCliente().getCep())
        .ibgeCode(sale.getCliente().getCodigoIbge())
        .phoneNumber(sale.getCliente().getTelefone())
        .email(sale.getCliente().getEmail())
        .build();
  }
}
