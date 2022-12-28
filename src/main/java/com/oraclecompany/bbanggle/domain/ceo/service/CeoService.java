package com.oraclecompany.bbanggle.domain.ceo.service;

import com.oraclecompany.bbanggle.api.login.dto.SignupDto;
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

    public Ceo signup(SignupDto.Request request) {
        Ceo ceo = new Ceo(request);
        ceo.encryptPassword(passwordEncoder);

        ceoRepository.save(ceo);
        return ceo;
    }

    public Ceo findCeoById(Long id) {
        return ceoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
    }
}
