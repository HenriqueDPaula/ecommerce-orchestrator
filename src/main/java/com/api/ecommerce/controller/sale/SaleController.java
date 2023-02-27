package com.api.ecommerce.controller.sale;

import javax.validation.Valid;

import com.api.ecommerce.controller.sale.dto.SaleRequest;
import com.api.ecommerce.controller.sale.dto.SaleResponse;
import com.api.ecommerce.driver.kafka.producer.SaleKafkaProducer;
import com.api.ecommerce.common.DateUtils;
import com.api.ecommerce.entity.enums.StatusEnum;
import com.api.ecommerce.usecase.saleauthorize.SaleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@ApiOperation("Sales API")
public class SaleController {

  private SaleService saleService;

  @Autowired
  public SaleController(SaleService saleService) {
    this.saleService = saleService;
  }

  @ApiOperation(value = "Authorize a Sale", notes = "Returns the status and response date")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "CREATED - Sale Authorization created successfully"),
      @ApiResponse(code = 400, message = "BAD REQUEST - Invalid request payload")
  })
  @PostMapping(value = "/autorizar-venda")
  public ResponseEntity<SaleResponse> authorize(@RequestBody @Valid SaleRequest saleRequest) {
    this.saleService.sendSaleAuthorizeToKafkaProducer(saleRequest);

    return new ResponseEntity<>(SaleResponse.builder().status(StatusEnum.PROCESSING.getValue()).responseDate(DateUtils.retrieveCurrentDateString()).build(),
        HttpStatus.CREATED);
  }
}
