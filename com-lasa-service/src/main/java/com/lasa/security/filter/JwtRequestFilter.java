package com.lasa.security.filter;

import com.lasa.security.utils.exception.ExceptionUtils.UserAccountException;
import com.lasa.security.utils.jwt.JwtConfig;
import com.lasa.security.utils.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final JwtConfig jwtConfig;
    private final UserDetailsService userDetailsService;
    private final String IS_USER_ENABLED = "isEnabled";
    private final String IS_USER_NON_LOCKED = "isAccountNonLocked";
    private final String USER_AUTHORITIES = "authorities";

    @Autowired
    public JwtRequestFilter(JwtUtil jwtUtil, JwtConfig jwtConfig, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.jwtConfig = jwtConfig;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        System.out.println(JwtRequestFilter.class.getSimpleName());
        String jwt = null;
        String username = null;
        final String authorization_token = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(authorization_token);
        if(authorization_token != null && authorization_token.startsWith(jwtConfig.getTokenPrefix() + " ")) {
            jwt = authorization_token.replace(jwtConfig.getTokenPrefix() + " ", "");
            username = jwtUtil.extractUsername(jwt);
        }
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if(!userDetails.isEnabled()) throw new UserAccountException("ACCOUNT_NOT_ENABLED");

            else if(!userDetails.isAccountNonLocked()) throw new UserAccountException("ACCOUNT_IS_LOCKED");

            else if(!userDetails.isAccountNonExpired()) throw new UserAccountException("ACCOUNT_IS_DELETED");

            else if(jwtUtil.validateToken(jwt, userDetails)) {

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );

                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
        }

        filterChain.doFilter(request, response);
    }
}
