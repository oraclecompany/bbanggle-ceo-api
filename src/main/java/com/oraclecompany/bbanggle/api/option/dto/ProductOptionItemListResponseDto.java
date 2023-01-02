package com.oraclecompany.bbanggle.api.option.dto;

import com.oraclecompany.bbanggle.api.product.dto.ProductDto;

import java.util.List;

public class ProductOptionItemListResponseDto {

    private Long productOptionGroupId;

    private List<ProductOptionItemDto> productOptionItemList;

    private List<ProductDto> product;

    public static ProductOptionItemDto of(ProductOptionItemDto productOptionItemDto) {
        return ProductOptionItemDto.builder()
                .productOptionGroupId(productOptionItemDto.getProductOptionGroupId())
//                .productOptionItemList(productOptionItemDto.getProductOptionGroupName())
//                .product(productOptionItemDto.getProduct())
                .build();
    }
}
