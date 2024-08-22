package com.blogger.user_service.Service;

import com.blogger.user_service.Model.Users;
import com.blogger.user_service.Repository.UserDao;
import com.blogger.user_service.utils.JWTHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class AuthService {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JWTHelper jwtHelper;

    @Autowired
    private UserDao userDao;

    public String login(String username, String password) {

        Authentication authenticate=manager.authenticate(new UsernamePasswordAuthenticationToken(username,password));

        if(authenticate.isAuthenticated()){
            UserDetails userDetails= (UserDetails) authenticate.getPrincipal();
            String Token= jwtHelper.generateToken(userDetails.getUsername(),userDetails.getAuthorities().toString());
            userDao.saveToken(Token,username);
            return Token;
        }
        return null;
    }

    public void register(Users users) throws Exception {
        users.setPassword(new BCryptPasswordEncoder(10).encode(users.getPassword()));
        users.setRoles(users.getRoles().stream().map(r->"ROLE_".concat(r.toUpperCase())).toList());
        userDao.save(users);
        log.info("user register successfully");
    }

    public Map<String, String> fetchToken(String username, String password) {
        Map<String, String> resp_map = new HashMap<>();
        try {
            Users details = userDao.findByUsername(username);

            if (details == null) {
                throw new RuntimeException("user not found");
            } else if (details.getToken().isEmpty()) {
                throw new RuntimeException("User not logged IN");
            }
            if (new BCryptPasswordEncoder(10).matches(password, details.getPassword())) {
                resp_map.put("Token", details.getToken());
                return resp_map;
            } else {
                resp_map.put("message", "Password Mismatched");
            }
        }catch (RuntimeException e){
            resp_map.put("message", e.getMessage());
        }
        return resp_map;
    }
}
