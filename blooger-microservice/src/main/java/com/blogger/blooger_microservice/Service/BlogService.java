package com.blogger.blooger_microservice.Service;

import com.blogger.blooger_microservice.Model.Blog;
import com.blogger.blooger_microservice.Repository.DaoBlog;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class BlogService {

    @Autowired
    private DaoBlog daoBlog;

    public Blog createBlog(Blog blog) throws PropertyValueException {
        blog.setCreated_date(LocalDate.now());
        log.info("Blog Crated Successfully");
        return daoBlog.save(blog);
    }


    public List<Blog> getAllBlogs() {
        log.info("fetching all blogs");
        return daoBlog.findAll();
    }
}
