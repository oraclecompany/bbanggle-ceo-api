package com.oraclecompany.bbanggle.domain.product.repository;

import com.oraclecompany.bbanggle.domain.product.entity.ProductOptionLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOptionLinkRepository extends JpaRepository<ProductOptionLink, Long>, ProductOptionGroupRepositoryCustom {
}
