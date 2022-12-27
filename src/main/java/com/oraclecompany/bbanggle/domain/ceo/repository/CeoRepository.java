package com.oraclecompany.bbanggle.domain.ceo.repository;

import com.oraclecompany.bbanggle.domain.ceo.entity.Ceo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CeoRepository extends JpaRepository<Ceo, Long> {

    Optional<Ceo> findByEmail(String email);

    Optional<Ceo> findByName(String nickname);

    Optional<Ceo> findByLoginId(String loginId);

}
