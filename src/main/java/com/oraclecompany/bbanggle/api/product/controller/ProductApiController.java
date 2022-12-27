package com.oraclecompany.bbanggle.api.product.controller;

import com.oraclecompany.bbanggle.api.product.dto.ProductListResponseDto;
import com.oraclecompany.bbanggle.api.product.service.ProductApiService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/mm")
@RestController
public class ProductApiController {

    private final ProductApiService productApiService;

    @ApiOperation(value = "상품 목록 조회 api", notes = "상품 목록 조회")
    @GetMapping("/products")
    public ResponseEntity<List<ProductListResponseDto>> getProductList(Pageable pageable) {
        return ResponseEntity.ok(productApiService.selectProductList(pageable));
    }
}
