package com.oraclecompany.bbanggle.api.auth.service;

import com.oraclecompany.bbanggle.api.auth.dto.CeoSignupRequestDto;
import com.oraclecompany.bbanggle.domain.ceo.service.CeoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class AuthService {
    private final CeoService ceoService;

    /**
     * 회원가입
     * @param request
     * @return 회원가입한 이메일
     * @author jaechan
     * @since 2021. 03. 10.
     */
    public String signup(CeoSignupRequestDto request) {
        return ceoService.signup(request);
    }
}
