package com.oraclecompany.bbanggle.api.product.dto;

import com.oraclecompany.bbanggle.domain.common.constant.YesOrNo;
import com.oraclecompany.bbanggle.domain.product.constant.DayCode;
import com.oraclecompany.bbanggle.domain.product.constant.SellStatus;
import com.oraclecompany.bbanggle.domain.product.entity.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductListResponseDto {

    //상품그룹명(카테고리)
    private String ProductGroupName;

    //상품식별자
    private Long id;

    //상품명
    private String name;

    //상품가격
    private Long price;

    //상품수량
    private int quantity;

    //상품상태
    private SellStatus sellStatus;

    //상품숨김여부
    private YesOrNo hiddenYn;

    //상품시간표요일코드
    private DayCode productTimeTableDayCode;

    //상품시간표식별자
    private Long productTimeTableId;
//    private ProductTimetable productTimetable;

    public static ProductListResponseDto of(ProductGroupLink productGroupLink) {
        return ProductListResponseDto.builder()
//                .productGroup(productGroupLink.getProductGroup())
                .ProductGroupName(productGroupLink.getProductGroup().getName())
                .id(productGroupLink.getProduct().getId())
                .name(productGroupLink.getProduct().getName())
                .price(productGroupLink.getProduct().getPrice())
                .quantity(productGroupLink.getProduct().getQuantity())
                .sellStatus(productGroupLink.getProduct().getStatus())
                .hiddenYn(productGroupLink.getProduct().getHiddenYn())
//                .productTimeTableDayCode(productTimetable.getDayCode())
//                .productTimeTableId(productTimetable.getId())
                .build();
    }
}
