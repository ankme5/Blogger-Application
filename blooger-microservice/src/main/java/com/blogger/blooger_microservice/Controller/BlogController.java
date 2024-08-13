package com.blogger.blooger_microservice.Controller;

import com.blogger.blooger_microservice.Model.Blog;
import com.blogger.blooger_microservice.Model.Response;
import com.blogger.blooger_microservice.Service.BlogService;
import org.springframework.beans.PropertyAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ResourceBundle;

@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    private final Response response=new Response();

    @PostMapping(value = "createBlog",produces = "application/json")
    ResponseEntity<Response> createBlog(@RequestBody Blog blog){
        Blog b=null;
        try {
            b = blogService.createBlog(blog);
            response.setHttpStatus(HttpStatus.CREATED);
            response.setMessage("Blog Created Successfully");
            response.setStatus_code(HttpStatus.CREATED.value());
            response.setBody(b);
        }catch (Exception ex){
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(ex.getMessage());
            response.setStatus_code(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("getAllBlogs")
    ResponseEntity<Response> getAllBlogs(){
        List<Blog> b=null;
        try {
            b = blogService.getAllBlogs();
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Blog Fetched Successfully");
            response.setStatus_code(HttpStatus.CREATED.value());
            response.setBody(b);
        }catch (Exception ex){
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(ex.getMessage());
            response.setStatus_code(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
