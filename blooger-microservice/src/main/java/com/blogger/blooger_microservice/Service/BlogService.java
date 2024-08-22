package com.blogger.blooger_microservice.Service;

import com.blogger.blooger_microservice.Model.Blog;
import com.blogger.blooger_microservice.Repository.DaoBlog;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BlogService {

    @Autowired
    private DaoBlog daoBlog;

    public Blog createBlog(Blog blog) throws PropertyValueException {
        blog.setCreatedDate(LocalDate.now());
        log.info("Blog Crated Successfully");
        return daoBlog.save(blog);
    }


    public List<Blog> getAllBlogs(Integer userId) {
        log.info("fetching all blogs");
        return daoBlog.findByUserId(userId);
    }

    public void updateBlog(Blog blog, Integer id) {
        log.info("updating blog");
        Optional<Blog> currentBlog= daoBlog.findById(id);

        if(currentBlog.isPresent()){
            if(blog.getBlogDetails()==null){
                blog.setBlogDetails(currentBlog.get().getBlogDetails());
            }
            if(blog.getBlogTitle()==null){
                blog.setBlogTitle(currentBlog.get().getBlogTitle());
            }
            if(blog.getLabels()==null){
                blog.setLabels(currentBlog.get().getLabels());
            }
            daoBlog.updateBlog(id,blog.getBlogTitle(),blog.getBlogDetails(),blog.getLabels());
        }
        else{
            log.info("Record with id "+ id+ " is not found");
        }

    }

    public void deleteBlog(Integer id) {
        daoBlog.deleteById(id);
        log.info("Blog Deleted with id "+id);
    }

    public Optional<Blog> getBlogById(Integer id) {
        return daoBlog.findById(id);
    }
}
