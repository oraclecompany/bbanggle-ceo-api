package com.oraclecompany.bbanggle.api.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CeoSignupRequestDto {
    private String email;
    private String password;
    private String name;
}
