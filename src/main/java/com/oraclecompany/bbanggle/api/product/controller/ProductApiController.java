package com.oraclecompany.bbanggle.api.product.controller;

import com.oraclecompany.bbanggle.api.product.dto.ProductListResponseDto;
import com.oraclecompany.bbanggle.api.product.dto.ProductQuantityUpdateDto;
import com.oraclecompany.bbanggle.api.product.service.ProductApiService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/mm")
@RestController
public class ProductApiController {

    private final ProductApiService productApiService;

    @ApiOperation(value = "상품 목록 조회 api", notes = "상품 목록 조회")
    @GetMapping("/products")
    public ResponseEntity<List<ProductListResponseDto>> getProductList(@RequestParam("id") Long id, Pageable pageable) {

        return ResponseEntity.ok(productApiService.selectProductList(pageable, id));
    }

    @ApiOperation(value = "상품 수량 변경 api", notes = "상품 수량 변경")
    @PatchMapping("/products/{productId}/quantity")
    public ResponseEntity<String> updateProductQuantity(
            @RequestBody @Valid ProductQuantityUpdateDto productQuantityUpdateDto,
            @PathVariable("productId") Long productId) {
        productApiService.updateProductQuantity(productId, productQuantityUpdateDto);
        return ResponseEntity.ok("수량이 변경되었습니다.");
    }

    @ApiOperation(value = "상품 수량 증가 api", notes = "상품 수량 증가")
    @PatchMapping("/products/{productId}/quantity/plus")
    public ResponseEntity<String> updateProductQuantityPlus(
            @PathVariable("productId") Long productId) {
        productApiService.updateProductQuantityPlus(productId);
        return ResponseEntity.ok("수량이 증가되었습니다.");
    }

    @ApiOperation(value = "상품 수량 감소 api", notes = "상품 수량 감소")
    @PatchMapping("/products/{productId}/quantity/minus")
    public ResponseEntity<String> updateProductQuantityMinus(
            @PathVariable("productId") Long productId) {
        productApiService.updateProductQuantityMinus(productId);
        return ResponseEntity.ok("수량이 감소되었습니다.");
    }
}
