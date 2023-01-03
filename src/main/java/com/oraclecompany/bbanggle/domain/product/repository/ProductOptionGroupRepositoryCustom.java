package com.oraclecompany.bbanggle.domain.product.repository;

import com.oraclecompany.bbanggle.domain.product.entity.Product;
import com.oraclecompany.bbanggle.domain.product.entity.ProductOptionGroup;
import com.oraclecompany.bbanggle.domain.product.entity.ProductOptionLink;
import com.oraclecompany.bbanggle.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductOptionGroupRepositoryCustom {
    Page<ProductOptionGroup> findByStore(Pageable pageable, Store store);

    ProductOptionGroup findByProductOptionGroupId(Long productOptionGroupId);
}
