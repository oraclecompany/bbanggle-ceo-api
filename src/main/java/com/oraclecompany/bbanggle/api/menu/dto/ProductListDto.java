package com.oraclecompany.bbanggle.api.menu.dto;

import com.oraclecompany.bbanggle.domain.common.constant.YesOrNo;
import com.oraclecompany.bbanggle.domain.product.constant.DayCode;
import com.oraclecompany.bbanggle.domain.product.constant.DayType;
import com.oraclecompany.bbanggle.domain.product.constant.SellStatus;
import com.oraclecompany.bbanggle.domain.product.entity.Product;
import com.oraclecompany.bbanggle.domain.product.entity.ProductGroup;
import com.oraclecompany.bbanggle.domain.product.entity.ProductTimetable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.List;

public class ProductListDto {

    @Builder
    @Getter
    @Setter
    public static class Response {

        @ApiModelProperty(value = "상품 ID", example = "1")
        private Long productId;
        @ApiModelProperty(value = "상품 그룹 리스트")
        private String ProductGroupName;
        @ApiModelProperty(value = "상품 이름", example = "상품A")
        private String productName;
        @ApiModelProperty(value = "상품 가격", example = "10000")
        private Long price;
        @ApiModelProperty(value = "상품 수량", example = "10")
        private int quantity;
        @ApiModelProperty(value = "상품 판매 상태", example = "SL", dataType = "SellStatus")
        private SellStatus sellStatus;
        @ApiModelProperty(value = "상품 숨김 여부", example = "N", dataType = "YesOrNo")
        private YesOrNo hiddenYn;
        @ApiModelProperty(value = "상품 시그니처 여부", example = "N", dataType = "YesOrNo")
        private YesOrNo signatureYn;
        @ApiModelProperty(value = "상품 시간표 리스트")
        private List<ProductTimetableDto> productTimeTableList;

        @Builder
        public static Response of(Product product) {
            return Response.builder()
                    .productId(product.getId())
                    .ProductGroupName(product.getProductGroup() != null ? product.getProductGroup().getName() : "")
                    .productName(product.getName())
                    .price(product.getPrice())
                    .quantity(product.getQuantity())
                    .sellStatus(product.getStatus())
                    .hiddenYn(product.getHiddenYn())
                    .signatureYn(product.getSignatureYn())
                    .productTimeTableList(
                            product.getProductTimetables()
                                    .stream()
                                    .map(ProductTimetableDto::of)
                                    .toList()
                    ).build();
        }
    }

    @Builder
    @Getter
    @Setter
    public static class ProductTimetableDto {

        @ApiModelProperty(value = "상품 시간표 ID", example = "1")
        private Long productTimetableId;
        @ApiModelProperty(value = "요일코드", example = "MON", dataType = "DayCode")
        private DayCode dayCode;
        @ApiModelProperty(value = "요일코드 설명", example = "월요일")
        private String dayCodeDesc;
        @ApiModelProperty(value = "시간유형", example = "AM", dataType = "DayType")
        private DayType dayType;
        @ApiModelProperty(value = "시간유형 설명", example = "오전")
        private String dayTypeDesc;
        @ApiModelProperty(value = "빵 나오는 시간", example = "10:00:00")
        private Time hhmm;

        @Builder
        public static ProductTimetableDto of(ProductTimetable productTimeTable) {
            return ProductTimetableDto.builder()
                    .productTimetableId(productTimeTable.getId())
                    .dayCode(productTimeTable.getDayCode())
                    .dayCodeDesc(productTimeTable.getDayCode().getDescription())
                    .dayType(productTimeTable.getDayType())
                    .dayTypeDesc(productTimeTable.getDayType().getDescription())
                    .hhmm(productTimeTable.getHhmm())
                    .build();
        }
    }

    @Builder
    @Getter
    @Setter
    public static class ProductGroupDto {

        @ApiModelProperty(value = "상품 그룹 ID", example = "1")
        private Long id;
        @ApiModelProperty(value = "상품 그룹 이름", example = "1")
        private String name;

        @Builder
        public static ProductGroupDto of(ProductGroup productGroup) {
            return ProductGroupDto.builder()
                    .id(productGroup.getId())
                    .name(productGroup.getName())
                    .build();
        }
    }

}
