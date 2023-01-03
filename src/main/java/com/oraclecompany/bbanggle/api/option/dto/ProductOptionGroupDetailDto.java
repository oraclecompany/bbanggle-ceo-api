package com.oraclecompany.bbanggle.api.option.dto;

import com.oraclecompany.bbanggle.domain.common.constant.YesOrNo;
import com.oraclecompany.bbanggle.domain.product.constant.SellStatus;
import com.oraclecompany.bbanggle.domain.product.entity.Product;
import com.oraclecompany.bbanggle.domain.product.entity.ProductOptionGroup;
import com.oraclecompany.bbanggle.domain.product.entity.ProductOptionItem;
import com.oraclecompany.bbanggle.domain.product.entity.ProductOptionLink;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ProductOptionGroupDetailDto {

    @Builder
    @Getter
    @Setter
    public static class Response {

        private Long productOptionGroupId;

        private String productOptionGroupName;

        private YesOrNo multiCheckYn;

        private List<ProductDto> products;

        private List<ProductOptionItemDto> productOptionItems;

        @Builder
        public static Response of(ProductOptionGroup productOptionGroup) {
            return Response.builder()
                    .productOptionGroupId(productOptionGroup.getId())
                    .productOptionGroupName(productOptionGroup.getName())
                    .multiCheckYn(productOptionGroup.getMultiCheckYn())
                    .products(
                            productOptionGroup
                                    .getProductOptionLinks()
                                    .stream()
                                    .map(ProductOptionLink::getProduct)
                                    .map(ProductDto::of)
                                    .toList()
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

    @Builder
    @Getter
    @Setter
    public static class ProductDto {

        private Long productId;

        private String name;

        public static ProductDto of(Product product) {
            return ProductDto.builder()
                    .productId(product.getId())
                    .name(product.getName())
                    .build();
        }
    }

    @Builder
    @Getter
    @Setter
    public static class ProductOptionItemDto {

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
}
