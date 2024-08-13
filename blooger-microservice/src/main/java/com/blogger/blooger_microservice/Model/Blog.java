package com.blogger.blooger_microservice.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Blog_details;
    @Column(nullable = false)
    private String Blog_title;
    private LocalDate created_date;
    private List<String> labels;
    }
