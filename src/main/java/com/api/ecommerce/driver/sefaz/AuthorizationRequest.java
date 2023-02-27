package com.api.ecommerce.driver.sefaz;

import java.util.List;

import com.api.ecommerce.controller.sale.dto.SaleRequest;
import com.api.ecommerce.usecase.saleauthorize.vo.Customer;
import com.api.ecommerce.usecase.saleauthorize.vo.Product;

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
public class AuthorizationRequest {

  private String orderNumber;
  private String externalOrderNumber;

  private Customer customer;

  private List<Product> products;

  public static AuthorizationRequest from(SaleRequest sale, List<Product> products) {
    return builder()
        .orderNumber(sale.getOrdemPedido().getNumeroPedido())
        .externalOrderNumber(sale.getOrdemPedido().getNumeroOrdemExterno())
        .customer(Customer.of(sale))
        .products(products)
        .build();
  }

}
