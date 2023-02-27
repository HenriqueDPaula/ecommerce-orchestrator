package com.api.ecommerce.controller.sefaz;

import com.api.ecommerce.common.DateUtils;
import com.api.ecommerce.driver.sefaz.AuthorizationRequest;
import com.api.ecommerce.driver.sefaz.AuthorizationResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SefazController {

  @PostMapping(value = "/authorize", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AuthorizationResponse> authorize(@RequestBody AuthorizationRequest authorizationRequest) {
    return new ResponseEntity<>(AuthorizationResponse.builder()
        .nfeKey("43210392754738001134550040000159551330237069")
        .invoiceNumber("0237069")
        .issuanceDate(DateUtils.retrieveDateFromString("2022-11-11T15:38:00.012"))
        .invoice("NDMyMTAzOTI3NTQ3MzgwMDExMzQ1NTAwNDAwMDAxNTk1NTEzMzAyMzcwNjk")
        .build(), HttpStatus.OK);
  }
}
