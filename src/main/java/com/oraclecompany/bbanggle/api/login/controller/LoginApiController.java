package com.oraclecompany.bbanggle.api.login.controller;

import com.oraclecompany.bbanggle.api.login.dto.SignupDto;
import com.oraclecompany.bbanggle.api.login.dto.JwtRequestDto;
import com.oraclecompany.bbanggle.api.login.service.LoginApiService;
import com.oraclecompany.bbanggle.jwt.dto.JwtTokenDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Api(value = "로그인 화면", tags = "로그인 화면")
public class LoginApiController {

    private final LoginApiService loginApiService;

    @ApiOperation(value = "회원가입 API", notes = "사장님 계정 회원가입을 진행한다.")
    @PostMapping(value = "signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SignupDto.Response> signup(@RequestBody SignupDto.Request request) {
        return ResponseEntity.ok(loginApiService.signup(request));
    }

    @ApiOperation(value = "로그인 API", notes = "사장님 로그인을 진행한다.")
    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtTokenDto> login(@RequestBody JwtRequestDto request) throws Exception {
        return ResponseEntity.ok(loginApiService.login(request));
    }
}

