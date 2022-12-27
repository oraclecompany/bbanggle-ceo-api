package com.oraclecompany.bbanggle.api.login.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequestDto {
    @ApiModelProperty(value = "로그인 ID", example = "test0001")
    private String loginId;
    @ApiModelProperty(value = "비밀번호", example = "a1234")
    private String password;
}
