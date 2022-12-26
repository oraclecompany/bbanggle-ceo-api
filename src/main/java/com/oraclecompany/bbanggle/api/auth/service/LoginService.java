package com.oraclecompany.bbanggle.api.auth.service;

import com.oraclecompany.bbanggle.api.auth.dto.CeoSignupDto;
import com.oraclecompany.bbanggle.api.auth.dto.JwtRequestDto;
import com.oraclecompany.bbanggle.api.auth.dto.JwtResponseDto;
import com.oraclecompany.bbanggle.domain.ceo.entity.Ceo;
import com.oraclecompany.bbanggle.domain.ceo.service.CeoService;
import com.oraclecompany.bbanggle.security.JwtTokenProvider;
import com.oraclecompany.bbanggle.security.UserDetailsImpl;
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
    private final JwtTokenProvider jwtTokenProvider;

    public CeoSignupDto.Response signup(CeoSignupDto.Request request) {
        Ceo eco = ceoService.signup(request);
        return CeoSignupDto.Response.of(eco.getLoginId());
    }

    public JwtResponseDto login(JwtRequestDto request) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLoginId(), request.getPassword()));

        return createJwtToken(authentication);
    }

    private JwtResponseDto createJwtToken(Authentication authentication) {
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        String token = jwtTokenProvider.generateToken(principal);
        return new JwtResponseDto(token);
    }
}
