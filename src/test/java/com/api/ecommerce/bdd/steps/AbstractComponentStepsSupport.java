package com.api.ecommerce.bdd.steps;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import com.api.ecommerce.controller.sale.dto.SaleRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class AbstractComponentStepsSupport {

  protected SaleRequest sale;

  protected LocalDateTime responseDate;

  private final ObjectMapper objectMapper;

  private final int BAD_REQUEST = 400;

  protected AbstractComponentStepsSupport() {
    objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
  }

  protected MvcResult executePost(MockMvc mockMvc, SaleRequest body, String path) throws Exception {
      return mockMvc.perform(post(path)
              .characterEncoding(StandardCharsets.UTF_8)
              .contentType(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(body)))
          .andDo(print())
          .andReturn();
  }

  protected <T> T readBodyAsObject(String body, Class<T> response) throws IOException {
    return objectMapper.readValue(body, response);
  }

  protected Boolean isBadRequest(int status) {
    return status == BAD_REQUEST;
  }

}
