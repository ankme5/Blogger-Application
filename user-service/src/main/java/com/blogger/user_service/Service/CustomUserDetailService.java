package com.blogger.user_service.Service;

import com.blogger.user_service.Model.Users;
import com.blogger.user_service.Repository.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.jaas.JaasGrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.switchuser.SwitchUserGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users=userDao.findByUsername(username);
        if(users==null){
            throw new UsernameNotFoundException("user not found");
        }
        log.info("user found in db");
        List<SimpleGrantedAuthority> authorities = users.getRoles().stream().map(SimpleGrantedAuthority::new).toList();
        return new User(users.getUsername(),users.getPassword(), authorities);
    }
}
