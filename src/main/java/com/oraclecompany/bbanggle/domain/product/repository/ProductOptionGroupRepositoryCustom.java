package com.oraclecompany.bbanggle.domain.product.repository;

import com.oraclecompany.bbanggle.domain.product.entity.ProductOptionGroup;
import com.oraclecompany.bbanggle.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductOptionGroupRepositoryCustom {
    Page<ProductOptionGroup> findByStore(Pageable pageable, Store store);

    ProductOptionGroup findByProductOptionGroupId(Long productOptionGroupId);
}
