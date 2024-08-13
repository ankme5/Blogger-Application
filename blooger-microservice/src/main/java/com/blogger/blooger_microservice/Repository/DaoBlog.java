package com.blogger.blooger_microservice.Repository;

import com.blogger.blooger_microservice.Model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoBlog extends JpaRepository<Blog,Long> {
}
