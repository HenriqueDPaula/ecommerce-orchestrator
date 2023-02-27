package com.api.ecommerce.driver.sefaz;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "sefazClient", url = "${sefaz-api.url}")
public interface SefazClient {

  @PostMapping(value = "/authorize")
  AuthorizationResponse authorize(@RequestBody AuthorizationRequest authorizationRequest);

}
