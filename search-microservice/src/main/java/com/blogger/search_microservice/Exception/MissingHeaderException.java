package com.blogger.search_microservice.Exception;

public class MissingHeaderException extends RuntimeException {
  public MissingHeaderException(String headerName) {
    super(headerName);
  }
}
