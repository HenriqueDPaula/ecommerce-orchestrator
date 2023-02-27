package com.api.ecommerce.driver.canal;

import java.util.Optional;

import com.api.ecommerce.entity.enums.StatusEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CanalService {

  private CanalClient canalClient;

  private final String EMPTY = "";

  @Autowired
  public void setCanalClient(CanalClient canalClient) {
    this.canalClient = canalClient;
  }

  public CallbackResponse executeSaleCallback(CallbackRequest callbackRequest) {
    return Optional.ofNullable(canalClient.executeSaleCallback(callbackRequest))
        .orElse(new CallbackResponse());
  }
}
