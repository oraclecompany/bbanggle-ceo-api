package com.oraclecompany.bbanggle.api.option.dto;

import com.oraclecompany.bbanggle.api.product.dto.ProductDto;
import com.oraclecompany.bbanggle.domain.common.constant.YesOrNo;
import com.oraclecompany.bbanggle.domain.product.entity.ProductOptionGroup;
import com.oraclecompany.bbanggle.domain.product.entity.ProductOptionLink;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
public class ProductOptionItemListResponseDto {

    private Long productOptionGroupId;

    private String productOptionGroupName;

    private YesOrNo multiCheckYn;

    private List<ProductDto> products;

    private List<ProductOptionItemDto> productOptionItems;

    public static ProductOptionItemListResponseDto of(ProductOptionGroup productOptionGroup) {
        return ProductOptionItemListResponseDto.builder()
                .productOptionGroupId(productOptionGroup.getId())
                .productOptionGroupName(productOptionGroup.getName())
                .multiCheckYn(productOptionGroup.getMultiCheckYn())
                .products(
                        productOptionGroup.getProductOptionLinks().stream()
                                .map(ProductOptionLink::getProduct)
                                .map(ProductDto::of)
                                .collect(Collectors.toList())
                )
                .productOptionItems(
                        productOptionGroup
                                .getProductOptionItems()
                                .stream()
                                .map(ProductOptionItemDto::of)
                                .toList())
                .build();
    }
}
