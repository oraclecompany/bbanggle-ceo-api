package com.oraclecompany.bbanggle.api.auth.dto;

import com.oraclecompany.bbanggle.domain.ceo.constant.CeoStatus;
import com.oraclecompany.bbanggle.domain.ceo.constant.CeoType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class SignupDto {
    @Getter
    @Setter
    public static class Request {
        @ApiModelProperty(value = "이름", example = "홍길동")
        private String name;
        @ApiModelProperty(value = "상점 ID", example = "1")
        private Long storeId;
        @ApiModelProperty(value = "로그인 ID", example = "test0001")
        private String loginId;
        @ApiModelProperty(value = "email", example = "jcjeong@oracleadinfo.com")
        private String email;
        @ApiModelProperty(value = "비밀번호", example = "a1234")
        private String password;
        @ApiModelProperty(value = "상태", example = "NORMAL")
        private CeoStatus status;
        @ApiModelProperty(value = "타입", example = "GENERAL")
        private CeoType type;
        @ApiModelProperty(value = "전화번호", example = "01012345678")
        private String tel;
    }

    @Builder
    @Getter @Setter
    public static class Response {
        private String loginId;

        public static Response of(String loginId) {
            return Response.builder()
                    .loginId(loginId)
                    .build();
        }
    }
}
