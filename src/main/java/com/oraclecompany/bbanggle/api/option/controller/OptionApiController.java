package com.oraclecompany.bbanggle.api.option.controller;

import com.oraclecompany.bbanggle.api.option.dto.ProductOptionItemListResponseDto;
import com.oraclecompany.bbanggle.api.option.dto.ProductOptionListResponseDto;
import com.oraclecompany.bbanggle.api.option.service.OptionApiService;
import com.oraclecompany.bbanggle.domain.product.entity.ProductOptionItem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/api/om")
@RestController
@Api(value = "옵션관리 화면", tags = "옵션관리 화면")
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

    @ApiOperation(value = "상품 옵션 아이템 목록 조회 api", notes = "상품 옵션 아이템 목록 조회")
    @GetMapping("/products/options/{optionId}/items")
    public ResponseEntity<ProductOptionItemListResponseDto> getProductOptionItems(
            @PathVariable("optionId") Long optionId) {
        ProductOptionItemListResponseDto productOptionItemListResponseDto = optionApiService.getProductOptionItemList(optionId);
        return ResponseEntity.ok(productOptionItemListResponseDto);
    }

    @ApiOperation(value = "상품 옵션 아이템 품절상태 변경 api", notes = "상품 옵션 아이템 품절상태 변경")
    @PatchMapping("/products/options/{optionId}/items/{itemId}")
    public ResponseEntity<String> updateProductOptionItemSellStatus(
            @PathVariable("itemId") Long itemId) {
        optionApiService.updateProductOptionItemSellStatus(itemId);
        return ResponseEntity.ok("픔절 싱테기 뱐경되었습니다.");
    }
}
