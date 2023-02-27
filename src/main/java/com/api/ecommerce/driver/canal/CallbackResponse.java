package com.api.ecommerce.driver.canal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CallbackResponse {

  private String message;

  private int status;

  public Boolean isResponseError() {
    return !isResponseSuccess();
  }

  public Boolean isResponseSuccess() {
    return this.getStatus() == 200;
  }
}
