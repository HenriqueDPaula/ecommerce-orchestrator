package com.api.ecommerce.controller.canal;

import com.api.ecommerce.driver.canal.CallbackRequest;
import com.api.ecommerce.driver.canal.CallbackResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CanalController {

  private String callbackMessage = "Venda %s recebida com sucesso";

  @PostMapping(value = "/callback-venda")
  public ResponseEntity<CallbackResponse> saleCallback(@RequestBody CallbackRequest callbackRequest) {
    CallbackResponse response =
        new CallbackResponse(String.format(callbackMessage, callbackRequest.getExternalOrderNumber()), HttpStatus.OK.value());

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
