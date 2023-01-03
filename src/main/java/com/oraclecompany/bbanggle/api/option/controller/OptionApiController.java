package com.oraclecompany.bbanggle.api.option.controller;

import com.oraclecompany.bbanggle.api.option.dto.ProductOptionGroupDetailDto;
import com.oraclecompany.bbanggle.api.option.dto.ProductOptionListDto;
import com.oraclecompany.bbanggle.api.option.service.OptionApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/api/om")
@RestController
@Api(value = "옵션관리 화면", tags = "옵션관리 화면")
public class OptionApiController {

    private final OptionApiService optionApiService;

    @ApiOperation(value = "상품 옵션 목록 조회 api", notes = "상품 옵션 목록 조회")
    @GetMapping("/{storeId}/products/options")
    public ResponseEntity<Page<ProductOptionListDto.Response>> getProductOptions(
            @PathVariable("storeId") Long storeId,
            Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.orElse(0), 5);
        Page<ProductOptionListDto.Response> productOptionListDto = optionApiService.getProductOptionList(storeId, pageable);
        return ResponseEntity.ok(productOptionListDto);
    }

    @ApiOperation(value = "상품 옵션 아이템 목록 조회 api", notes = "상품 옵션 아이템 목록 조회")
    @GetMapping("/products/options/{productOptionGroupId}/items")
    public ResponseEntity<ProductOptionGroupDetailDto.Response> getProductOptionGroupDetails(
            @PathVariable("productOptionGroupId") Long productOptionGroupId) {
        ProductOptionGroupDetailDto.Response productOptionGroupDetailDto = optionApiService.getProductOptionGroupDetails(productOptionGroupId);
        return ResponseEntity.ok(productOptionGroupDetailDto);
    }

    @ApiOperation(value = "상품 옵션 아이템 품절상태 변경 api", notes = "상품 옵션 아이템 품절상태 변경")
    @PatchMapping("/products/options/items/{itemId}")
    public ResponseEntity<String> updateProductOptionItemSellStatus(
            @PathVariable("itemId") Long itemId) {
        optionApiService.updateProductOptionItemSellStatus(itemId);
        return ResponseEntity.ok("품절 상태가 변경되었습니다.");
    }
}
