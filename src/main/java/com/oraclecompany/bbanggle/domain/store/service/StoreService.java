package com.oraclecompany.bbanggle.domain.store.service;

import com.oraclecompany.bbanggle.domain.store.entity.Store;
import com.oraclecompany.bbanggle.domain.store.repository.StoreRepository;
import com.oraclecompany.bbanggle.global.error.exception.EntityNotFoundException;
import com.oraclecompany.bbanggle.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public Store findStoreById(Long storeId) {
            return storeRepository.findById(storeId)
                    .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_EXIST_STORE));
    }
}
