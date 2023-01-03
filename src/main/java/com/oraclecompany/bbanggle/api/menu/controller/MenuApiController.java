package com.oraclecompany.bbanggle.api.menu.controller;

import com.oraclecompany.bbanggle.api.menu.dto.ProductListDto;
import com.oraclecompany.bbanggle.api.menu.dto.ProductQuantityUpdateDto;
import com.oraclecompany.bbanggle.api.menu.service.MenuApiService;
import com.oraclecompany.bbanggle.global.resolver.ceoinfo.CeoInfoDto;
import com.oraclecompany.bbanggle.global.resolver.ceoinfo.CeoInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/api/mm")
@RestController
@Api(value = "메뉴관리 화면", tags = "메뉴관리 화면")
public class MenuApiController {

    private final MenuApiService menuApiService;

    @ApiOperation(value = "상품 목록 조회 api", notes = "상품 목록 조회")
    @GetMapping("/products")
    public ResponseEntity<Page<ProductListDto.Response>> getProductList(
            Optional<Integer> page,
            @CeoInfo CeoInfoDto ceoInfoDto) {
        Pageable pageable = PageRequest.of(page.orElse(0), 5);
        return ResponseEntity.ok(menuApiService.getProductList(pageable, ceoInfoDto));
    }

    @ApiOperation(value = "상품 수량 변경 api", notes = "상품 수량 변경")
    @PatchMapping("/products/{productId}/quantity")
    public ResponseEntity<String> updateProductQuantity(
            @RequestBody @Valid ProductQuantityUpdateDto productQuantityUpdateDto,
            @PathVariable("productId") Long productId) {
        menuApiService.updateProductQuantity(productId, productQuantityUpdateDto);
        return ResponseEntity.ok("수량이 변경되었습니다.");
    }

    @ApiOperation(value = "상품 수량 증가 api", notes = "상품 수량 증가")
    @PatchMapping("/products/{productId}/quantity/plus")
    public ResponseEntity<String> updateProductQuantityPlus(
            @PathVariable("productId") Long productId) {
        menuApiService.updateProductQuantityPlus(productId);
        return ResponseEntity.ok("수량이 증가되었습니다.");
    }

    @ApiOperation(value = "상품 수량 감소 api", notes = "상품 수량 감소")
    @PatchMapping("/products/{productId}/quantity/minus")
    public ResponseEntity<String> updateProductQuantityMinus(
            @PathVariable("productId") Long productId) {
        menuApiService.updateProductQuantityMinus(productId);
        return ResponseEntity.ok("수량이 감소되었습니다.");
    }

    @ApiOperation(value = "상품 품절 상태 변경 api", notes = "상품 품절 상태 변경")
    @PatchMapping("/products/{productId}/status")
    public ResponseEntity<String> updateProductSellStatus(
            @PathVariable("productId") Long productId) {
        menuApiService.updateProductSellStatus(productId);
        return ResponseEntity.ok("상품 품절 상태가 변경되었습니다.");
    }
}
