package com.oraclecompany.bbanggle.jwt.service;

import com.oraclecompany.bbanggle.api.login.constant.Role;
import com.oraclecompany.bbanggle.global.error.exception.AuthenticationException;
import com.oraclecompany.bbanggle.global.error.exception.ErrorCode;
import com.oraclecompany.bbanggle.global.error.exception.NotValidTokenException;
import com.oraclecompany.bbanggle.jwt.constant.TokenType;
import com.oraclecompany.bbanggle.jwt.dto.JwtTokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Component
public class TokenManager {

    @Value("${token.access-token-expiration-time}")
    private String accessTokenExpirationTime;

    @Value("${token.refresh-token-expiration-time}")
    private String refreshTokenExpirationTime;

    @Value("${token.secret}")
    private String tokenSecret;

    public JwtTokenDto createJwtTokenDto(Long memberId, Role role, String loginId) {
        Date accessTokenExpireTime = createAccessTokenExpireTime();
        Date refreshTokenExpireTime = createRefreshTokenExpireTime();

        String accessToken = createAccessToken(memberId, role, loginId, accessTokenExpireTime);
        String refreshToken = createRefreshToken(memberId, refreshTokenExpireTime);

        return JwtTokenDto.builder()
                .accessToken(accessToken)
                .accessTokenExpireTime(accessTokenExpireTime)
                .refreshToken(refreshToken)
                .refreshTokenExpireTime(refreshTokenExpireTime)
                .build();
    }

    public Date createAccessTokenExpireTime() {
        return new Date(System.currentTimeMillis() + Long.parseLong(accessTokenExpirationTime));
    }

    public Date createRefreshTokenExpireTime() {
        return new Date(System.currentTimeMillis() + Long.parseLong(refreshTokenExpirationTime));
    }

    public String createAccessToken(Long ceoId, Role role, String loginId, Date expirationTime) {
        return Jwts.builder()
                .setSubject(TokenType.ACCESS.name())                // 토큰 제목
                .setIssuedAt(new Date())                            // 토큰 발급 시간
                .setExpiration(expirationTime)                      // 토큰 만료 시간
                .claim("ceoId", ceoId)                        // 회원 아이디
                .claim("role", role)                          // 유저 role
                .claim("loginId", loginId)                    // 유저 loginId
                .signWith(SignatureAlgorithm.HS512, tokenSecret.getBytes(StandardCharsets.UTF_8))
                .setHeaderParam("typ", "JWT")
                .compact();
    }

    public String createRefreshToken(Long memberId, Date expirationTime) {
        return Jwts.builder()
                .setSubject(TokenType.REFRESH.name())               // 토큰 제목
                .setIssuedAt(new Date())                            // 토큰 발급 시간
                .setExpiration(expirationTime)                      // 토큰 만료 시간
                .signWith(SignatureAlgorithm.HS512, tokenSecret.getBytes(StandardCharsets.UTF_8))
                .setHeaderParam("typ", "JWT")
                .compact();
    }

    public void validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(tokenSecret.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            log.info("token 만료", e);
            throw new AuthenticationException(ErrorCode.ACCESS_TOKEN_EXPIRED);
        } catch (Exception e) {
            log.info("유효하지 않은 token", e);
            throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);
        }
    }

    public Claims getTokenClaims(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(tokenSecret.getBytes(StandardCharsets.UTF_8))  //jwt 만들 때 사용했던 키
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            throw new NotValidTokenException(ErrorCode.NOT_VALID_TOKEN);
        }
        return claims;
    }

    public boolean isTokenExpired(Date tokenExpiredTime) {
        Date now = new Date();
        return now.after(tokenExpiredTime);
    }
}
