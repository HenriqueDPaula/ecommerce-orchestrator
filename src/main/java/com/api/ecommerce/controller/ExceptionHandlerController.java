package com.api.ecommerce.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlerController {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  public ResponseEntity<ExceptionResponse> processValidationException(final
                                                              MethodArgumentNotValidException ex) {

    String errorMessage = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(field -> convertMessageError(field.getField(), field.getDefaultMessage()))
        .sorted()
        .collect(Collectors.joining(", ", "", "."));

    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    ExceptionResponse exceptionResponse = new ExceptionResponse(httpStatus.getReasonPhrase(), errorMessage);

    return new ResponseEntity<>(exceptionResponse, httpStatus);
  }

  /**
   * @param field object.innerObject
   * @param message innerObject must not be null
   * @return object.innerObject must not be null
   */
  private String convertMessageError(String field, String message) {
    return field + " " + message.split(" ", 2)[1];
  }

}
