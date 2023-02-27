package com.api.ecommerce.bdd.transformer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import com.api.ecommerce.controller.sale.dto.SaleRequest;
import com.api.ecommerce.driver.canal.CallbackResponse;
import com.api.ecommerce.driver.sefaz.AuthorizationResponse;
import com.api.ecommerce.driver.tributary.Tribute;
import com.api.ecommerce.entity.SaleTable;
import com.api.ecommerce.entity.enums.StatusEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.cucumber.java.DataTableType;

import static com.api.ecommerce.bdd.transformer.FieldExtract.extractDoubleFromRow;
import static com.api.ecommerce.bdd.transformer.FieldExtract.extractIntFieldFromRow;
import static com.api.ecommerce.bdd.transformer.FieldExtract.extractStringFieldFromRow;

public class ProcessSaleAuthorizeTransformer {

  @DataTableType
  public Tribute tributeTransform(Map<String, String> row) {
    return Tribute.builder()
        .sku(extractIntFieldFromRow(row, "Sku"))
        .valorDifaul(extractDoubleFromRow(row, "Valor Difaul"))
        .valorIcms(extractDoubleFromRow(row, "Valor Icms"))
        .valorPis(extractDoubleFromRow(row, "Valor Pis"))
        .valorFcpIcms(extractDoubleFromRow(row, "Valor Fcp Icms"))
        .build();
  }

  @DataTableType
  public AuthorizationResponse authorizationResponseTransform(Map<String, String> row) {
    return AuthorizationResponse.builder()
        .nfeKey(extractStringFieldFromRow(row, "Nfe Key"))
        .invoice(extractStringFieldFromRow(row, "Invoice"))
        .issuanceDate(LocalDateTimeTransformer.transformWithMili(row, "Issuance Date"))
        .invoiceNumber(extractStringFieldFromRow(row, "Invoice Number"))
        .build();
  }

  @DataTableType
  public CallbackResponse callbackResponseTransform(Map<String, String> row) {
    String message = extractStringFieldFromRow(row, "Mensagem");
    int statusCode = message.contains("sucesso") ? 200 : 500;
    return new CallbackResponse(message, statusCode);
  }

  @DataTableType
  public SaleTable saleTransform(Map<String, String> row) throws IOException {
    SaleTable sale = new SaleTable();
    sale.setChannel(extractStringFieldFromRow(row, "Canal"));
    sale.setCompanyCode(extractIntFieldFromRow(row, "Codigo Empresa"));
    sale.setStoreCode(extractIntFieldFromRow(row, "Codigo Loja"));
    sale.setPdvNumber(extractIntFieldFromRow(row, "Numero Pdv"));
    sale.setOrderNumber(extractStringFieldFromRow(row, "Numero Pedido"));
    sale.setExternalOrderNumber(extractStringFieldFromRow(row, "Numero Ordem Externo"));
    sale.setTotalValue(extractDoubleFromRow(row, "Valor Total"));
    sale.setItemAmount(extractIntFieldFromRow(row, "Quantidade Itens"));
    sale.setSaleRequest(retrieveSaleRequestFromJson(extractStringFieldFromRow(row, "Venda Request")));
    sale.setUpdateDate(LocalDateTimeTransformer.transform(row, "Data Atualizacao"));
    sale.setRequestDate(LocalDateTimeTransformer.transformWithMili(row, "Data Requisicao"));
    sale.setNfeKey(extractStringFieldFromRow(row, "Chave NFE"));
    sale.setInvoiceNumber(extractIntFieldFromRow(row, "Numero Nota"));
    sale.setIssuanceDate(LocalDateTimeTransformer.transform(row, "Data Emissao"));
    sale.setPdf(extractStringFieldFromRow(row, "Pdf"));
    sale.setStatus(StatusEnum.findByValue(extractStringFieldFromRow(row, "Situacao")).getValue());
    sale.setReason(extractStringFieldFromRow(row, "Motivo"));
    return sale;
  }

  private SaleRequest retrieveSaleRequestFromJson(String path) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    return mapper.readValue(extractJsonFileAsString(path), SaleRequest.class);
  }

  private String extractJsonFileAsString(String path) throws IOException {
    String fullPath = "src/test/resources" + path;
    return new String(Files.readAllBytes(Paths.get(fullPath)));
  }
}
