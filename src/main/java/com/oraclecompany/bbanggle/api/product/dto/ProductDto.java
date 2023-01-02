package com.oraclecompany.bbanggle.api.product.dto;

import com.oraclecompany.bbanggle.domain.product.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductDto {

    private Long productId;

    private String name;

    public static ProductDto of(Product product) {
        return ProductDto.builder()
                .productId(product.getId())
                .name(product.getName())
                .build();
    }
}
