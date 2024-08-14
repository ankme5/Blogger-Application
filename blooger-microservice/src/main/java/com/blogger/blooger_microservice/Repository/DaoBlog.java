package com.blogger.blooger_microservice.Repository;

import com.blogger.blooger_microservice.Model.Blog;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaoBlog extends JpaRepository<Blog,Integer> {


    @Transactional
    @Modifying
    @Query("update Blog b set b.blog_details= :blogDetails,b.blog_title= :blogTitle,b.labels= :labels where b.id= :id")
    void updateBlog(Integer id, String blogTitle, String blogDetails, List<String> labels);
}
