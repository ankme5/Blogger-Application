package com.blogger.user_service.Service;

import com.blogger.user_service.Model.Users;
import com.blogger.user_service.Repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public Users fetchUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

}
