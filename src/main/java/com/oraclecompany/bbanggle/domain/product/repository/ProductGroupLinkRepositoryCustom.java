package com.oraclecompany.bbanggle.domain.product.repository;

import com.oraclecompany.bbanggle.domain.product.entity.Product;
import com.oraclecompany.bbanggle.domain.product.entity.ProductGroupLink;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductGroupLinkRepositoryCustom {

    Page<ProductGroupLink> findByProduct(Pageable pageable, List<Product> product);
}
