package com.api.ecommerce.bdd.transformer;

import java.util.Map;

import com.api.ecommerce.controller.ExceptionResponse;
import com.api.ecommerce.controller.sale.dto.Customer;
import com.api.ecommerce.controller.sale.dto.Item;
import com.api.ecommerce.controller.sale.dto.Order;
import com.api.ecommerce.controller.sale.dto.SaleRequest;
import com.api.ecommerce.controller.sale.dto.SaleResponse;

import io.cucumber.java.DataTableType;

import static com.api.ecommerce.bdd.transformer.FieldExtract.extractIntFieldFromRow;
import static com.api.ecommerce.bdd.transformer.FieldExtract.extractPersonTypeFromRow;
import static com.api.ecommerce.bdd.transformer.FieldExtract.extractStringFieldFromRow;
import static com.api.ecommerce.bdd.transformer.LocalDateTimeTransformer.transformWithMili;

public class SaleAuthorizeTransformer {

  @DataTableType
  public SaleRequest transformSale(Map<String, String> row) {
    return SaleRequest.builder()
        .canal(extractStringFieldFromRow(row, "Canal"))
        .empresa(extractStringFieldFromRow(row, "Empresa"))
        .loja(extractStringFieldFromRow(row, "Loja"))
        .pdv(extractIntFieldFromRow(row, "Pdv"))
        .totalItens(extractIntFieldFromRow(row, "Total Itens"))
        .quantidadeItens(extractIntFieldFromRow(row, "Quantidade Itens"))
        .build();
  }

  @DataTableType
  public Order transformOrder(Map<String, String> row) {
    return Order.builder()
        .numeroPedido(extractStringFieldFromRow(row, "Numero Pedido"))
        .numeroOrdemExterno(extractStringFieldFromRow(row, "Numero Ordem Externo"))
        .dataAutorizacao(transformWithMili(row, "Data Autorizacao"))
        .build();
  }

  @DataTableType
  public Customer transformCustomer(Map<String, String> row) {
    return Customer.builder()
        .id(extractStringFieldFromRow(row, "Id"))
        .nome(extractStringFieldFromRow(row, "Nome"))
        .documento(extractStringFieldFromRow(row, "Documento"))
        .tipoDocumento(extractStringFieldFromRow(row, "Tipo Documento"))
        .tipoPessoa(extractPersonTypeFromRow(row, "Tipo Pessoa"))
        .endereco(extractStringFieldFromRow(row, "Endereco"))
        .numeroEndereco(extractStringFieldFromRow(row, "Numero Endereco"))
        .complementoEndereco(extractStringFieldFromRow(row, "Complemento Endereco"))
        .bairro(extractStringFieldFromRow(row, "Bairro"))
        .cidade(extractStringFieldFromRow(row, "Cidade"))
        .estado(extractStringFieldFromRow(row, "Estado"))
        .pais(extractStringFieldFromRow(row, "Pais"))
        .cep(extractStringFieldFromRow(row, "Cep"))
        .codigoIbge(extractStringFieldFromRow(row, "Codigo Ibge"))
        .telefone(extractStringFieldFromRow(row, "Telefone"))
        .email(extractStringFieldFromRow(row, "Email"))
        .build();
  }

  @DataTableType
  public Item transformItems(Map<String, String> row) {
    return Item.builder()
        .sku(extractIntFieldFromRow(row, "Sku"))
        .quantidade(extractIntFieldFromRow(row, "Quantidade"))
        .valor(extractIntFieldFromRow(row, "Valor"))
        .build();
  }

  @DataTableType
  public SaleResponse transformSaleResponse(Map<String, String> row) {
    return SaleResponse.builder()
        .status(extractStringFieldFromRow(row, "Status"))
        .responseDate(extractStringFieldFromRow(row, "Data Resposta"))
        .build();
  }

  @DataTableType
  public ExceptionResponse transformException(Map<String, String> row) {
    return new ExceptionResponse(extractStringFieldFromRow(row, "Http Status"),
        extractStringFieldFromRow(row, "Message"));
  }
}
