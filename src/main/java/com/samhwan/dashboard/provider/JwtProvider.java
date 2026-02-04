package com.samhwan.dashboard.provider;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtProvider {

    @Value("${secret-key}")
    private String secretKey;


    //생성
    public String create(String userId){

        // 한시간
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
        
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        
        String jwt = Jwts.builder()
            .setSubject(String.valueOf(userId))
            .setSubject(userId).setIssuedAt(new Date()).setExpiration(expiredDate)
            .signWith(key,SignatureAlgorithm.HS256)
            .compact();
        
            return jwt;
    }


    //검증
    public String validate(String jwt){

        Claims claims = null;
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        
        try{
            claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        }catch(ExpiredJwtException  e){
            log.debug("JWT expired. exp={}, now={}", e.getClaims().getExpiration(), new java.util.Date());
            return null;
        }catch (UnsupportedJwtException | MalformedJwtException | SecurityException | IllegalArgumentException e) {
            // 위조/깨짐/형식오류: 필요하면 warn 한 줄
            log.warn("Invalid JWT: {}", e.getClass().getSimpleName());
            return null;

        } catch (JwtException e) {
            // 그 외 JWT 파싱 관련 예외
            log.warn("JWT parse error: {}", e.getClass().getSimpleName());
            return null;
        }
        return claims.getSubject();
    }
    @PostConstruct
    public void checkJwtVersion() {
        System.out.println(">>> JJWT Version: " +
        io.jsonwebtoken.Jwt.class.getPackage().getImplementationVersion());
    }

}
