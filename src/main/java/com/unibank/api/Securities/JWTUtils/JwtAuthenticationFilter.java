package com.unibank.api.Securities.JWTUtils;

import com.unibank.api.Securities.UniBankUserDetailsServices;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JwtToken jwtToken;
    private final UniBankUserDetailsServices uniBankUserDetailsServices;

    public JwtAuthenticationFilter(JwtToken jwtToken, UniBankUserDetailsServices uniBankUserDetailsServices){
        this.jwtToken = jwtToken;
        this.uniBankUserDetailsServices = uniBankUserDetailsServices;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // getting the token from the authHeader ==>
        // The client doesn't have to send Bearer
        String authHeader = request.getHeader("Authorization");

        // to check if the client really sent the token or not
        if (authHeader != null){

            // if the client sent the token then extract the token from the authHeader
           String userName = jwtToken.extract(authHeader);

            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = uniBankUserDetailsServices.loadUserByUsername(userName);

                if (jwtToken.isTokenValid(authHeader, userDetails)){
                    UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );

                    SecurityContextHolder.getContext().setAuthentication(userToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
