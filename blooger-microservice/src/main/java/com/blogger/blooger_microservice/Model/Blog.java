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
    private Integer id;
    @Column(name = "blog_details")
    private String blogDetails;
    @Column(nullable = false,unique = true,name = "blog_title")
    private String blogTitle;
    @Column(name = "created_date")
    private LocalDate createdDate;
    @Column(name = "labels")
    private List<String> labels;
    @Column(name = "user_id")
    private Integer userId;
}
