package com.blogger.blooger_microservice.Filters;

import com.blogger.blooger_microservice.Client.UserServiceClient;
import com.blogger.blooger_microservice.Model.Users;
import com.blogger.blooger_microservice.utils.JWTHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class JwtVerifyFilter extends OncePerRequestFilter {

    @Autowired
    private JWTHelper jwtHelper;

    @Autowired
    private UserServiceClient userServiceClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String auth_header=request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        try {
            if (auth_header != null && auth_header.startsWith("Bearer ")) {
                jwt = auth_header.substring(7);
                username = jwtHelper.extractUsername(jwt);
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                Users user=userServiceClient.fetchUserByUsername(username);
                Boolean ValidateToken = jwtHelper.validateToken(jwt, user.getUsername());
                if (ValidateToken) {
                    List<SimpleGrantedAuthority> authorityList=user.getRoles().stream().map(SimpleGrantedAuthority::new).toList();

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(user.getUsername(), null, authorityList);
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    log.info("Validation Fails");
                }
                log.info("JWT verification filter SUCCESS");

            }
        }catch (JwtException ex){
            throw new RuntimeException("JWT Not Verify");
        }

        filterChain.doFilter(request,response);

    }
}
