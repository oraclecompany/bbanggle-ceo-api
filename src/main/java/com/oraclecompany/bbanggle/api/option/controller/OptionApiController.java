package com.oraclecompany.bbanggle.api.option.controller;

import com.oraclecompany.bbanggle.api.option.dto.ProductOptionListResponseDto;
import com.oraclecompany.bbanggle.api.option.service.OptionApiService;
import com.oraclecompany.bbanggle.domain.product.entity.ProductOptionItem;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/api/om")
@RestController
public class OptionApiController {

    private final OptionApiService optionApiService;

    @ApiOperation(value = "상품 옵션 목록 조회 api", notes = "상품 옵션 목록 조회")
    @GetMapping("/{storeId}/products/options")
    public ResponseEntity<List<ProductOptionListResponseDto>> getProductOptions(
            @PathVariable("storeId") Long storeId,
            Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.orElse(0), 5);
        List<ProductOptionListResponseDto> productOptionListResponseDto = optionApiService.getProductOptionList(storeId, pageable);

        return ResponseEntity.ok(productOptionListResponseDto);
    }
}
