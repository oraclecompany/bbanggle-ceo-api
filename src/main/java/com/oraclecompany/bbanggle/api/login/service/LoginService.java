package com.oraclecompany.bbanggle.api.login.service;

import com.oraclecompany.bbanggle.api.login.dto.JwtRequestDto;
import com.oraclecompany.bbanggle.api.login.dto.SignupDto;
import com.oraclecompany.bbanggle.domain.ceo.entity.Ceo;
import com.oraclecompany.bbanggle.domain.ceo.service.CeoService;
import com.oraclecompany.bbanggle.jwt.dto.JwtTokenDto;
import com.oraclecompany.bbanggle.jwt.service.TokenManager;
import com.oraclecompany.bbanggle.jwt.dto.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class LoginService {
    private final CeoService ceoService;

    private final AuthenticationManager authenticationManager;
    private final TokenManager tokenManager;

    public SignupDto.Response signup(SignupDto.Request request) {
        Ceo eco = ceoService.signup(request);
        return SignupDto.Response.of(eco.getLoginId());
    }

    public JwtTokenDto login(JwtRequestDto request) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLoginId(), request.getPassword()));

        return createJwtToken(authentication);
    }

    private JwtTokenDto createJwtToken(Authentication authentication) {
        try {
            UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
            return tokenManager.createJwtTokenDto(principal.getId(), principal.getRole());
        } catch (Exception e) {
            return new JwtTokenDto(e.getMessage());
        }
    }
}
