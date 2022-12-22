package com.oraclecompany.bbanggle.domain.ceo.entity;

import com.oraclecompany.bbanggle.api.auth.dto.CeoSignupRequestDto;
import com.oraclecompany.bbanggle.domain.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "CEO")
public class Ceo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;

    @Column(name = "store_id")
    private String storeId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "type")
    private String type;

    @Column(name = "tel")
    private String tel;

    @Column(name = "role")
    private String role;

    public Ceo(CeoSignupRequestDto request) {
        email = request.getEmail();
        password = request.getPassword();
        name = request.getName();
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }

}
