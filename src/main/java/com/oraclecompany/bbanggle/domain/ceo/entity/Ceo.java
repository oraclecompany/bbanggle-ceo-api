package com.oraclecompany.bbanggle.domain.ceo.entity;

import com.oraclecompany.bbanggle.api.login.constant.Role;
import com.oraclecompany.bbanggle.api.login.dto.SignupDto;
import com.oraclecompany.bbanggle.domain.ceo.constant.CeoStatus;
import com.oraclecompany.bbanggle.domain.ceo.constant.CeoType;
import com.oraclecompany.bbanggle.domain.common.BaseEntity;
import com.oraclecompany.bbanggle.domain.store.entity.Store;
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
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    private String name;

    private String loginId;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CeoType ceoType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CeoStatus ceoStatus;

    private String tel;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Ceo(SignupDto.Request request) {
        this.name = request.getName();
        this.loginId = request.getLoginId();
        this.email = request.getEmail();
        this.password = request.getPassword();
        this.ceoStatus = request.getCeoStatus();
        this.ceoType = request.getCeoType();
        this.tel = request.getTel();
        this.role = Role.ROLE_USER;
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }

}
