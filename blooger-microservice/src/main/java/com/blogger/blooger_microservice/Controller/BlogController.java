package com.blogger.blooger_microservice.Controller;

import com.blogger.blooger_microservice.Model.Blog;
import com.blogger.blooger_microservice.Model.Response;
import com.blogger.blooger_microservice.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("blog")
public class BlogController {

    @Autowired
    private BlogService blogService;


    @PostMapping(value = "create")
    ResponseEntity<Response> createBlog(@RequestBody Blog blog){
        Response response=new Response();
        try {
            Blog b= blogService.createBlog(blog);
            response.setHttpStatus(HttpStatus.CREATED);
            response.setMessage("Blog Created Successfully");
            response.setStatus_code(HttpStatus.CREATED.value());
            response.setBody(b);
        }catch (Exception ex){
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(ex.getMessage());
            response.setStatus_code(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("find/{id}")
    ResponseEntity<Optional<Blog>> getBlogById(@PathVariable Integer id){
        return ResponseEntity.ok(blogService.getBlogById(id));
    }

    @GetMapping("{userId}/find")
    ResponseEntity<Response> findAllBlogs(@PathVariable Integer userId){
        Response response=new Response();
        try {
            List<Blog> b = blogService.findAllBlogs(userId);
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Blog Fetched Successfully");
            response.setStatus_code(HttpStatus.CREATED.value());
            response.setBody(b);
        }catch (Exception ex){
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(ex.getMessage());
            response.setStatus_code(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return new ResponseEntity<>(response, response.getHttpStatus());
    }


    @PutMapping("update/{id}")
    ResponseEntity<Response> updateBlog(@RequestBody Blog blog, @PathVariable Integer id){
        Response response=new Response();
        try {
            blogService.updateBlog(blog,id);
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Blog Updated Successfully");
            response.setStatus_code(HttpStatus.OK.value());
        }catch (Exception ex){
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(ex.getMessage());
            response.setStatus_code(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @DeleteMapping("delete/{id}")
    ResponseEntity<Response> deleteBlog(@PathVariable Integer id){
        Response response=new Response();
        try {
            blogService.deleteBlog(id);
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Blog Deleted Successfully");
            response.setStatus_code(HttpStatus.OK.value());
        }catch (Exception ex){
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(ex.getMessage());
            response.setStatus_code(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
