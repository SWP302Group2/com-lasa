package com.lasa.security.jwt;

import com.lasa.security.appuser.MyUserDetails;
import com.lasa.security.utils.ExceptionUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JwtUtil {
    private final JwtConfig jwtConfig;
    private final String IS_USER_ENABLED = "isEnabled";
    private final String IS_USER_NON_LOCKED = "isAccountNonLocked";
    private final String USER_AUTHORITIES = "authorities";

    @Autowired
    public JwtUtil(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    public Boolean isEnabled(String token) {
        return (Boolean) extractAllClaims(token).get(IS_USER_ENABLED);
    }

    public Boolean isAccountNonLocked(String token) {
        return (Boolean) extractAllClaims(token).get(IS_USER_NON_LOCKED);
    }

    public List<GrantedAuthority> authorities(String token) {
        return (List<GrantedAuthority>) extractAllClaims(token).get(USER_AUTHORITIES);
    }

    public Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch (ExpiredJwtException ex) {
            throw new ExceptionUtils.TokenException("TOKEN_IS_EXPIRED");
        }
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(MyUserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(IS_USER_NON_LOCKED, userDetails.isAccountNonLocked() );
        claims.put(IS_USER_ENABLED, userDetails.isEnabled());
        claims.put(USER_AUTHORITIES, userDetails.getAuthorities());
        return jwtConfig.getTokenPrefix() + " " + createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getTokenExpirationAfter()))
                .signWith(Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes()))
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) throws ExceptionUtils.TokenException {
        String username = extractUsername(token);
        return  (username.equals(userDetails.getUsername()));
    }

}
