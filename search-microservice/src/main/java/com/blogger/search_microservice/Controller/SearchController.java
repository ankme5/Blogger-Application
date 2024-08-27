package com.blogger.search_microservice.Controller;

import com.blogger.search_microservice.Model.Blog;
import com.blogger.search_microservice.Service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("search")
    List<Blog> search(String content){
        return searchService.search(content);
    }


}
