package com.api.ecommerce.driver.tributary;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TributaryService {

  private TributaryClient client;

  public Tribute retrieveTributeFromSku(Integer sku) {
    return Optional.ofNullable(this.client.retrieveTribute(sku))
        .orElse(new Tribute());
  }

  @Autowired
  public void setClient(TributaryClient client) {
    this.client = client;
  }

}
