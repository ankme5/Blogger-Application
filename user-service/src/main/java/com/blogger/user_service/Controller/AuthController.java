package com.blogger.user_service.Controller;

import com.blogger.user_service.Model.Response;
import com.blogger.user_service.Model.Users;
import com.blogger.user_service.Service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("v1/auth/users")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("login")
    ResponseEntity<Response> login(@RequestHeader String username,@RequestHeader String password){
        Response response=new Response();
        try {
            log.info("Start Authentication");
            String accessToken = authService.login(username, password);
            response.setBody(accessToken);
            response.setMessage("login success");
            response.setHttpStatus(HttpStatus.ACCEPTED);
            response.setStatus_code(HttpStatus.ACCEPTED.value());
            response.setBody(accessToken);
        }catch (Exception e){
            log.debug(e.getMessage());
            log.info("Login Failed");
            response.setMessage("Login Failed");
            response.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
            response.setStatus_code(HttpStatus.NOT_ACCEPTABLE.value());
        }

        return new ResponseEntity<>(response,response.getHttpStatus());
    }

    @PostMapping("register")
    ResponseEntity<Response> register(@RequestBody Users users){
        Response response=new Response();
        try {
            log.info("Start Registration");
            authService.register(users);
            response.setMessage("Register Successful");
            response.setHttpStatus(HttpStatus.CREATED);
            response.setStatus_code(HttpStatus.CREATED.value());
        } catch (Exception e) {
            log.debug(e.getMessage());
            log.info("Registration Failed");
            response.setMessage("Registration Failed");
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatus_code(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("{id}")
    ResponseEntity<Optional<Users>> fetchUserById(@PathVariable Integer id){
        return ResponseEntity.ok(authService.fetchUserById(id));
    }

    @GetMapping("fetchToken")
    ResponseEntity<Map<String, String>> fetchToken(@RequestHeader String username,@RequestHeader String password){
        Map<String,String> token = Map.of();
    try{
        log.info("Fetching Token");
         token=authService.fetchToken(username,password);
    } catch (Exception e) {
        log.debug(e.getMessage());
        log.info("Not able to Fetch Token");
    }
    return new ResponseEntity<>(token,HttpStatus.OK);
    }
}
