package com.oraclecompany.bbanggle.api.common.service;

import com.oraclecompany.bbanggle.api.common.dto.StoreStatusModifyDto;
import com.oraclecompany.bbanggle.domain.store.entity.Store;
import com.oraclecompany.bbanggle.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CommonApiService {

    private final StoreService storeService;

    public void modifyStoreStatus(StoreStatusModifyDto storeStatusModifyDto) {
        Store findStore = storeService.findStoreById(storeStatusModifyDto.getStoreId());
        findStore.modifyStatus(storeStatusModifyDto.getStoreStatus());
    }
}
