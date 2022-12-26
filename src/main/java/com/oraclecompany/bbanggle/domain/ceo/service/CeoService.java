package com.oraclecompany.bbanggle.domain.ceo.service;

import com.oraclecompany.bbanggle.api.auth.dto.CeoSignupDto;
import com.oraclecompany.bbanggle.domain.ceo.entity.Ceo;
import com.oraclecompany.bbanggle.domain.ceo.repository.CeoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CeoService {
    private final CeoRepository ceoRepository;
    private final PasswordEncoder passwordEncoder;

    public Ceo signup(CeoSignupDto.Request request) {
        Ceo ceo = new Ceo(request);
        ceo.encryptPassword(passwordEncoder);

        ceoRepository.save(ceo);
        return ceo;
    }
}
