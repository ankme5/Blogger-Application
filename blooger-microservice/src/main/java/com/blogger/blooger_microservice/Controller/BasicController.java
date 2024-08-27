package com.blogger.blooger_microservice.Controller;

import com.blogger.blooger_microservice.Model.Blog;
import com.blogger.blooger_microservice.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("basic")
public class BasicController {

    @Autowired
    private BlogService blogService;

    @GetMapping("get/allblogs")
    List<Blog> getAllBlogs(){
        return blogService.getAllBlogs();
    }
}
