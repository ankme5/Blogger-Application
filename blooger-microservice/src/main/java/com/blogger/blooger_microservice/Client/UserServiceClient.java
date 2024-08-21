package com.blogger.blooger_microservice.Client;

import com.blogger.blooger_microservice.Model.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service",path = "v1/user")
public interface UserServiceClient {

    @GetMapping("getUser/{username}")
    Users fetchUserByUsername(@PathVariable String username);
}
