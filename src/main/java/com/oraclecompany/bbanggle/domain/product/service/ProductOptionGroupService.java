package com.oraclecompany.bbanggle.domain.product.service;

import com.oraclecompany.bbanggle.domain.product.entity.ProductOptionGroup;
import com.oraclecompany.bbanggle.domain.product.repository.ProductOptionGroupRepository;
import com.oraclecompany.bbanggle.domain.store.entity.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductOptionGroupService {

    private final ProductOptionGroupRepository productOptionGroupRepository;

    public Page<ProductOptionGroup> findProductOptionList(Pageable pageable, Store store) {
        return productOptionGroupRepository.findByStore(pageable, store);
    }

    public ProductOptionGroup findProductOptionGroupById(Long optionId) {
        return productOptionGroupRepository.findByProductOptionGroupId(optionId);
    }
}
