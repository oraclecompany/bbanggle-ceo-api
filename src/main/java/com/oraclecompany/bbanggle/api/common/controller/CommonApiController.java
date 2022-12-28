package com.oraclecompany.bbanggle.api.common.controller;

import com.oraclecompany.bbanggle.api.common.dto.StoreStatusModifyDto;
import com.oraclecompany.bbanggle.api.common.service.CommonApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cm")
@RequiredArgsConstructor
public class CommonApiController {

    private final CommonApiService commonApiService;

    @PatchMapping("/stores/status")
    public ResponseEntity<String> modifyStoreStatus(@RequestBody StoreStatusModifyDto storeStatusModifyDto) {
        // todo : storeId를 향후에는 dto로 받지 않고, jwt 토큰을 통해 storeId를 받아오도록 수정
        commonApiService.modifyStoreStatus(storeStatusModifyDto);
        return ResponseEntity.ok("매장의 영업상태가 변경되었습니다.");
    }


}