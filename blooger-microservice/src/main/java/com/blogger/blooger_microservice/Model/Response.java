package com.blogger.blooger_microservice.Model;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Response {
    private int status_code;
    private String message;
    private HttpStatus httpStatus;
    private Object body;
}
