package com.oraclecompany.bbanggle.api.option.dto;

import com.oraclecompany.bbanggle.domain.product.constant.SellStatus;
import com.oraclecompany.bbanggle.domain.product.entity.ProductOptionItem;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductOptionItemDto {

    private Long productOptionItemId;

    private String name;

    private Long price;

    private int seq;

    private Long productOptionGroupId;

    private String productOptionGroupName;

    private SellStatus status;

    public static ProductOptionItemDto of(ProductOptionItem productOptionItem) {
        return ProductOptionItemDto.builder()
                .productOptionItemId(productOptionItem.getId())
                .name(productOptionItem.getName())
                .price(productOptionItem.getPrice())
                .seq(productOptionItem.getSeq())
                .productOptionGroupId(productOptionItem.getProductOptionGroup().getId())
                .productOptionGroupName(productOptionItem.getProductOptionGroup().getName())
                .status(productOptionItem.getStatus())
                .build();
    }
}
