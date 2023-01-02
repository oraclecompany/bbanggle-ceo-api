package com.oraclecompany.bbanggle.domain.product.repository;

import com.oraclecompany.bbanggle.domain.product.entity.ProductOptionItem;
import com.oraclecompany.bbanggle.domain.product.entity.ProductOptionLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOptionItemRepository extends JpaRepository<ProductOptionItem, Long> {
}