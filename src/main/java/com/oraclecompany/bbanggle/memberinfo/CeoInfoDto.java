package com.oraclecompany.bbanggle.memberinfo;

import com.oraclecompany.bbanggle.api.login.constant.Role;
import com.oraclecompany.bbanggle.domain.ceo.entity.Ceo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CeoInfoDto {

    private Long ceoId;
    private Role role;

    public static CeoInfoDto of(Ceo ceo) {
        return CeoInfoDto.builder()
                .ceoId(ceo.getId())
                .role(ceo.getRole())
                .build();
    }
}
