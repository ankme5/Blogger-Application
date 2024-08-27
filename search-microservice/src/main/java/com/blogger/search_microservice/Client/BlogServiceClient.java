package com.blogger.search_microservice.Client;

import com.blogger.search_microservice.Model.Blog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "blogger-microservice",path = "basic")
public interface BlogServiceClient {

    @GetMapping("get/allblogs")
    List<Blog> getAllBlogs();

}
