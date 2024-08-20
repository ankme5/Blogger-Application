package com.blogger.user_service.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Entity
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true,nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false,updatable = false)
    private List<String> roles= List.of("USER");
    @Column(updatable = true)
    private String email;
    @Column(updatable = true)
    private String token;
    @Column(updatable = true,length = 1)
    private Character isActive='Y';

}
