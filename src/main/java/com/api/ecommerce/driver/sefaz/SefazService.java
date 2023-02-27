package com.api.ecommerce.driver.sefaz;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SefazService {

  private SefazClient sefazClient;

  public AuthorizationResponse retrieveSefazAuthorizationResponse(AuthorizationRequest authorizationRequest) {
    return Optional.ofNullable(this.sefazClient.authorize(authorizationRequest))
        .orElse(new AuthorizationResponse());
  }

  @Autowired
  public void setSefazClient(SefazClient sefazClient) {
    this.sefazClient = sefazClient;
  }
}
