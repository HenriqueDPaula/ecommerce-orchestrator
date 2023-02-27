package com.api.ecommerce.usecase.saleauthorize;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.api.ecommerce.controller.sale.dto.Item;
import com.api.ecommerce.controller.sale.dto.SaleRequest;
import com.api.ecommerce.driver.canal.CallbackRequest;
import com.api.ecommerce.driver.canal.CallbackResponse;
import com.api.ecommerce.driver.canal.CanalService;
import com.api.ecommerce.driver.kafka.producer.SaleKafkaProducer;
import com.api.ecommerce.driver.sefaz.AuthorizationRequest;
import com.api.ecommerce.driver.sefaz.AuthorizationResponse;
import com.api.ecommerce.driver.sefaz.SefazService;
import com.api.ecommerce.driver.tributary.TributaryService;
import com.api.ecommerce.driver.tributary.Tribute;
import com.api.ecommerce.entity.SaleTable;
import com.api.ecommerce.entity.enums.StatusEnum;
import com.api.ecommerce.usecase.saleauthorize.repository.SaleRepository;
import com.api.ecommerce.usecase.saleauthorize.vo.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleService {

  private SaleKafkaProducer saleKafkaProducer;

  private TributaryService tributeService;

  private SefazService sefazService;

  private CanalService canalService;

  private SaleRepository saleRepository;

  private final String SALE_ERROR_RECEIVED_MESSAGE = "Venda %s recebida com erro";

  @Autowired
  public SaleService(SaleKafkaProducer saleKafkaProducer, TributaryService tributeService, SefazService sefazService,
                     CanalService canalService, SaleRepository saleRepository) {
    this.saleKafkaProducer = saleKafkaProducer;
    this.tributeService = tributeService;
    this.sefazService = sefazService;
    this.canalService = canalService;
    this.saleRepository = saleRepository;
  }

  public void sendSaleAuthorizeToKafkaProducer(SaleRequest saleRequest) {
    this.saleKafkaProducer.execute(saleRequest);
  }

  public void processSaleAuthorize(SaleRequest saleRequest) {

    AuthorizationRequest authorizationRequest = buildAuthorizationRequest(saleRequest);
    AuthorizationResponse authorizationResponse = sefazService.retrieveSefazAuthorizationResponse(authorizationRequest);

    CallbackRequest callbackRequest = buildCallbackRequest(authorizationRequest, authorizationResponse);
    CallbackResponse callbackResponse = canalService.executeSaleCallback(callbackRequest);

    SaleTable saleEntity =
        processCallbackResponse(callbackRequest, callbackResponse, saleRequest, authorizationRequest, authorizationResponse);

    persistSale(saleEntity);

  }

  private void persistSale(SaleTable saleTable) {
    this.saleRepository.save(saleTable);
  }

  private SaleTable processCallbackResponse(CallbackRequest callbackRequest, CallbackResponse callbackResponse, SaleRequest saleRequest,
                                            AuthorizationRequest authorizationRequest, AuthorizationResponse authorizationResponse) {
    StatusEnum statusProcessing = callbackRequest.getStatus();
    String reason = callbackResponse.getMessage();

    if (callbackResponse.isResponseError()) {
      statusProcessing = StatusEnum.PROCESSING_ERROR;
      reason = String.format(SALE_ERROR_RECEIVED_MESSAGE, callbackRequest.getExternalOrderNumber());
    }

    return buildSaleAuthorizeEntity(saleRequest, authorizationRequest, authorizationResponse, callbackRequest, statusProcessing, reason);
  }

  private AuthorizationRequest buildAuthorizationRequest(SaleRequest saleRequest) {
    return AuthorizationRequest.from(saleRequest, retrieveProducts(saleRequest.getItens()));
  }

  private CallbackRequest buildCallbackRequest(AuthorizationRequest authorizationRequest, AuthorizationResponse authorizationResponse) {
    return CallbackRequest.from(authorizationResponse, authorizationRequest.getOrderNumber(), authorizationRequest.getExternalOrderNumber(),
        StatusEnum.PROCESSED);
  }

  private List<Product> retrieveProducts(List<Item> items) {
    return items.stream().map(this::retrieveProduct).collect(Collectors.toList());
  }

  private Product retrieveProduct(Item item) {
    Tribute tribute = retrieveTribute(item);
    return Product.of(tribute, item.getQuantidade(), (item.getValor() * 1.0 / 100));
  }

  private Tribute retrieveTribute(Item item) {
    return this.tributeService.retrieveTributeFromSku(item.getSku());
  }

  private SaleTable buildSaleAuthorizeEntity(SaleRequest sale, AuthorizationRequest authorizationRequest,
                                             AuthorizationResponse authorizationResponse,
                                             CallbackRequest callbackRequest, StatusEnum statusEnum, String reason) {
    return SaleTable.builder()
        .id(Long.valueOf(sale.getCliente().getId()))
        .channel(sale.getCanal())
        .companyCode(Integer.valueOf(sale.getEmpresa()))
        .storeCode(Integer.valueOf(sale.getLoja()))
        .pdvNumber(sale.getPdv())
        .orderNumber(sale.getOrdemPedido().getNumeroPedido())
        .externalOrderNumber(sale.getOrdemPedido().getNumeroOrdemExterno())
        .totalValue(authorizationRequest.getProducts().stream().mapToDouble(Product::getValue).sum())
        .itemAmount(authorizationRequest.getProducts().stream().mapToInt(Product::getAmount).sum())
        .saleRequest(sale)
        .updateDate(LocalDateTime.now())
        .requestDate(sale.getOrdemPedido().getDataAutorizacao())
        .nfeKey(authorizationResponse.getNfeKey())
        .invoiceNumber(Integer.valueOf(authorizationResponse.getInvoiceNumber()))
        .issuanceDate(authorizationResponse.getIssuanceDate())
        .pdf(callbackRequest.getPdf())
        .status(statusEnum.getValue())
        .reason(reason)
        .build();
  }
}
