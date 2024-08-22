package com.blogger.blooger_microservice.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class Users {

    private int id;
    private String username;
    private String password;
    private List<String> roles;
    private String email;
    private String token;
    private Character isActive='Y';

}
