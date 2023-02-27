package com.api.ecommerce.driver.tributary;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "tributaryClient", url = "${tribute-api.url}")
public interface TributaryClient {

  @GetMapping(value = "/tributo")
  Tribute retrieveTribute(@RequestParam(value = "sku") Integer sku);
}
