package com.oraclecompany.bbanggle.api.menu.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;

@NoArgsConstructor
@Getter
@Setter
public class ProductQuantityUpdateDto {

    @Min(value = 0, message = "수량은 0 이상이어야 합니다.")
    @ApiModelProperty(value = "수량", required = true, example = "1")
    private int quantity;
}
