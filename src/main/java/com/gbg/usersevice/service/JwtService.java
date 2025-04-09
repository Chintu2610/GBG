package com.gbg.usersevice.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.gbg.usersevice.logout.BlackList;
import com.gbg.usersevice.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
    @Autowired
    private BlackList blackList;
    
    @Autowired
    UserInfoService userinfoservice;

    private static final String SECRET = "!@#$FDGSDFGSGSGSGSHSHSHSSHGFFDSGSFGSSGHSDFSDFSFSFSFSDFSFSFSF";

    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        
        Optional<User> user = Optional.of(new User());
        
        user = userinfoservice.getUsersByEmail(email);
        
       // String role = user.getRoles();
       // String password = user.getPassword();
       // System.out.println(role);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
               // .setAudience(role)
              //  .setAudience(password)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token) && !blackList.isBlackListed(token));
    }
    
    
}