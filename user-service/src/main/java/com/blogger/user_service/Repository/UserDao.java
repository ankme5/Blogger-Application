package com.blogger.user_service.Repository;

import com.blogger.user_service.Model.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<Users,Integer> {

    Users findByUsername(String username);

    @Transactional
    @Modifying
    @Query("update Users u set u.token= :token where u.username= :username ")
    void saveToken(String token, String username);
}
