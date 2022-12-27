package com.oraclecompany.bbanggle.domain.product.repository;

import com.oraclecompany.bbanggle.domain.product.entity.ProductGroupLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductGroupLinkRepository extends JpaRepository<ProductGroupLink, Long>, ProductGroupLinkRepositoryCustom {

}
