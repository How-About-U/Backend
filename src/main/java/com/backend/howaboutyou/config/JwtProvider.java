package com.backend.howaboutyou.config;


import com.backend.howaboutyou.domain.RefreshToken;
import com.backend.howaboutyou.domain.Token;
import com.backend.howaboutyou.exception.RefreshTokenMismatchException;
import com.backend.howaboutyou.exception.entity.ErrorCode;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${spring.jwt.password}")
    private String secretKey;

    // 토큰 생성
    public Token createAccessToken(String subject) {
        Date now = new Date();
        long accessTokenValidTime = Duration.ofMinutes(30).toMillis();
        long refreshTokenValidTime = Duration.ofDays(14).toMillis();

        String accessToken = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("HowAboutYou") // 토큰 발급자
                .setIssuedAt(now) // 발급시간
                .setExpiration(new Date(now.getTime() + accessTokenValidTime)) // 만료기간
                .setSubject(subject) // 토큰 제목
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes())) // 암호화 알고리즘
                .compact();

        String refreshToken = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("HowAboutYou")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshTokenValidTime))
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .compact();

        return Token.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .key(subject).build();
    }

    // 토큰 유효성 검사
    public String validateRefreshToken(RefreshToken refreshTokenObj) {
        String refreshTokenString = refreshTokenObj.getRefreshTokenString();
        Claims claims = Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .parseClaimsJws(refreshTokenString)
                .getBody();
        if (!claims.getSubject().equals(refreshTokenObj.getKeyEmail())) {
            throw new RefreshTokenMismatchException(ErrorCode.REFRESHTOKEN_MISMATCH);
        }
        // refresh 토큰의 만료시간이 지나지 않았을 경우, 새로운 Access Token 생성 및 반환
        if (!claims.getExpiration().before(new Date())) {
            return recreationAccessToken(claims.get("sub").toString());
        }
        return null;
    }

    public String recreationAccessToken(String email) {
        Claims claims = Jwts.claims().setSubject(email);
        Date now = new Date();
        long accessTokenValidTime = Duration.ofMinutes(30).toMillis();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + accessTokenValidTime))
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .compact();
    }

    // 정보가 담긴 부분 가져오기
    public String getUserEmail(String accessToken) {
        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .parseClaimsJws(accessToken)
                .getBody()
                .getSubject();
    }

    /*String[] chunks = token.split("\\.");
    Base64.Decoder decoder = Base64.getUrlDecoder();
    String header = new String(decoder.decode(chunks[0]));
    String payload = new String(decoder.decode(chunks[1]));
    return payload.split("sub")[1]
            .replaceAll("\"","")
            .replaceAll(":","")
            .replaceAll("}","");*/


    // Bearer 자르기
    private static String BearerRemove(String token) {
        return token.substring("Bearer ".length());
    }
}
