package com.bilgeadam.banket.config.security;

import com.bilgeadam.banket.utility.JwtTokenManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenManager jwtTokenManager;

    @Autowired
    private JwtUserDetails jwtUserDetails;

    private static final int TOKEN_BEGINNING_INDEX = 7;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> optionalBearerToken = Optional.ofNullable(request.getHeader("Authorization"));
        String token = null;
        String uuid = null;
        if(optionalBearerToken.isPresent() && optionalBearerToken.get().startsWith("Bearer ")) {
            token = optionalBearerToken.get().substring(TOKEN_BEGINNING_INDEX);
            uuid = jwtTokenManager.getUUIDFromTokenAsString(token);
        }
        if (uuid != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = jwtUserDetails.loadUserByUsername(uuid);
            if(jwtTokenManager.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

}
