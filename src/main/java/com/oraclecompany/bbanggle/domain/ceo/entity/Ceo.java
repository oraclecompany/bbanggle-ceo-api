package com.oraclecompany.bbanggle.domain.ceo.entity;

import com.oraclecompany.bbanggle.api.login.constant.Role;
import com.oraclecompany.bbanggle.api.login.dto.SignupDto;
import com.oraclecompany.bbanggle.domain.ceo.constant.CeoStatus;
import com.oraclecompany.bbanggle.domain.ceo.constant.CeoType;
import com.oraclecompany.bbanggle.domain.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Ceo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private  Long storeId;

    private String name;

    private String loginId;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private CeoType type;

    @Enumerated(EnumType.STRING)
    private CeoStatus status;

    private String tel;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Ceo(SignupDto.Request request) {
        this.name = request.getName();
        this.storeId = request.getStoreId();
        this.loginId = request.getLoginId();
        this.email = request.getEmail();
        this.password = request.getPassword();
        this.status = request.getStatus();
        this.type = request.getType();
        this.tel = request.getTel();
        this.role = Role.ROLE_USER;
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }

}
