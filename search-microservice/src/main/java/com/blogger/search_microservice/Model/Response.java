package com.blogger.search_microservice.Model;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Response {
    private int status_code;
    private String message;
    private HttpStatus httpStatus;
    private Object body;
}
