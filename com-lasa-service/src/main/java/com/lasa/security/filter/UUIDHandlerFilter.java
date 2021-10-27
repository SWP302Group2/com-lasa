package com.lasa.security.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class UUIDHandlerFilter extends OncePerRequestFilter {
    private final String UUID_HEADER = "uuid";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println(UUIDHandlerFilter.class.getSimpleName());
        String uuid = request.getHeader(UUID_HEADER);
        if (Objects.nonNull(uuid)){
            response.setHeader(UUID_HEADER, uuid);
        }
        filterChain.doFilter(request, response);
    }
}
