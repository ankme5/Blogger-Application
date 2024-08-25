package com.blogger.blooger_microservice.Exception;

public class MissingHeaderException extends RuntimeException {
    public MissingHeaderException(String headerName) {
        super(headerName);
    }
}
