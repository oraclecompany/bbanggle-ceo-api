package com.oraclecompany.bbanggle.security;

import com.oraclecompany.bbanggle.domain.ceo.entity.Ceo;
import com.oraclecompany.bbanggle.domain.ceo.repository.CeoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CeoRepository ceoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Ceo ceo = ceoRepository.findById(1L)
                .orElseThrow(()-> new UsernameNotFoundException("등록되지 않은 사용자 입니다"));

        return new UserDetailsImpl(ceo);
    }
}
