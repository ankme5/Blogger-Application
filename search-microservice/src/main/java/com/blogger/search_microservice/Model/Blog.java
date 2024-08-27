package com.blogger.search_microservice.Model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Blog {
    private Integer id;
    private String blogDetails;
    private String blogTitle;
    private LocalDate createdDate;
    private List<String> labels;
}
