package com.api.ecommerce.controller.tribute;

import java.util.concurrent.ThreadLocalRandom;

import com.api.ecommerce.driver.tributary.Tribute;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TributaryController {

  @GetMapping(value = "/tributo")
  public ResponseEntity<Tribute> retrieveTribute(@RequestParam(value = "sku") Integer sku) {
    return new ResponseEntity<>(Tribute.builder()
        .sku(sku)
        .valorIcms(randomValue())
        .valorPis(randomValue())
        .valorDifaul(randomValue())
        .valorFcpIcms(randomValue())
        .build(), HttpStatus.OK);
  }

  private Double randomValue() {
    return ThreadLocalRandom.current().nextDouble(1, 100);
  }
}
