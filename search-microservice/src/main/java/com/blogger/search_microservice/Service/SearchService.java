package com.blogger.search_microservice.Service;

import com.blogger.search_microservice.Client.BlogServiceClient;
import com.blogger.search_microservice.Model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private BlogServiceClient blogServiceClient;

    public List<Blog> search(String content) {
        List<Blog> records=blogServiceClient.getAllBlogs();
        return records;
    }
}
