package com.oraclecompany.bbanggle.domain.store.service;

import com.oraclecompany.bbanggle.domain.store.entity.Store;
import com.oraclecompany.bbanggle.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public Store findStoreById(Long storeId) {

            return storeRepository.findById(storeId).orElseThrow(() -> new IllegalArgumentException("해당 스토어가 없습니다. id=" + storeId));
    }
}
