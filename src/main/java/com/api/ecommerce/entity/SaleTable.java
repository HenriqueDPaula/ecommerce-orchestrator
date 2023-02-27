package com.api.ecommerce.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.api.ecommerce.controller.sale.dto.SaleRequest;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

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
@Entity
@Table(name = "VENDA_TESTE", schema = "HUB_FISCAL_OWNER")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class SaleTable {

  @Id
  @Column(name = "ID", nullable = false, unique = true)
  @GeneratedValue(generator="HUB_FISCAL_OWNER.VENDA_TESTE_SEQ", strategy= GenerationType.SEQUENCE)
  private Long id;

  @Column(name = "CANAL", nullable = false)
  private String channel;

  @Column(name = "CODIGO_EMPRESA", nullable = false)
  private Integer companyCode;

  @Column(name = "CODIGO_LOJA", nullable = false)
  private Integer storeCode;

  @Column(name = "NUMERO_PDV", nullable = false)
  private Integer pdvNumber;

  @Column(name = "NUMERO_PEDIDO", nullable = false)
  private String orderNumber;

  @Column(name = "NUMERO_ORDEM_EXTERNO", nullable = false)
  private String externalOrderNumber;

  @Column(name = "VALOR_TOTAL", nullable = false)
  private Double totalValue;

  @Column(name = "QTD_ITEM", nullable = false)
  private Integer itemAmount;

  @Type(type = "jsonb")
  @Column(name = "VENDA_REQUEST", columnDefinition = "jsonb", nullable = false)
  private SaleRequest saleRequest;

  @Column(name = "DATA_ATUALIZACAO", nullable = false)
  private LocalDateTime updateDate;

  @Column(name = "DATA_REQUISICAO", nullable = false)
  private LocalDateTime requestDate;

  @Column(name = "CHAVE_NFE")
  private String nfeKey;

  @Column(name = "NUMERO_NOTA")
  private Integer invoiceNumber;

  @Column(name = "DATA_EMISSAO")
  private LocalDateTime issuanceDate;

  @Column(name = "PDF")
  private String pdf;

  @Column(name = "SITUACAO", nullable = false)
  private String status;

  @Column(name = "MOTIVO")
  private String reason;
}


