package com.api.ecommerce.driver.canal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${canal-api.url}", name = "canalClient")
public interface CanalClient {

  @PostMapping(value = "/callback-venda")
  CallbackResponse executeSaleCallback(@RequestBody CallbackRequest callbackRequest);

}
