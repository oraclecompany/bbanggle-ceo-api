package com.oraclecompany.bbanggle.jwt.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter @Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JwtTokenDto {

    private String accessToken;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private Date accessTokenExpireTime;

    private String refreshToken;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private Date refreshTokenExpireTime;

    public JwtTokenDto(String message) {

    }
}