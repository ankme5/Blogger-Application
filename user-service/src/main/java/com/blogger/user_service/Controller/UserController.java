package com.blogger.user_service.Controller;

import com.blogger.user_service.Model.Users;
import com.blogger.user_service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("getUser/{username}")
    Users fetchUserByUsername(@PathVariable String username){
        return userService.fetchUserByUsername(username);
    }


}

