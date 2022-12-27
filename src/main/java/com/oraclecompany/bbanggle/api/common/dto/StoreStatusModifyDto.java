package com.oraclecompany.bbanggle.api.common.dto;

import com.oraclecompany.bbanggle.domain.store.constant.StoreStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class StoreStatusModifyDto {
    private Long storeId;
    private StoreStatus storeStatus;
}
