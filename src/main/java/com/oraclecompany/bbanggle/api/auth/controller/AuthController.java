package com.oraclecompany.bbanggle.api.auth.controller;

import com.oraclecompany.bbanggle.api.auth.dto.CeoSignupRequestDto;
import com.oraclecompany.bbanggle.api.auth.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public String signup(@RequestBody CeoSignupRequestDto request) {
        return authService.signup(request);
    }

}
