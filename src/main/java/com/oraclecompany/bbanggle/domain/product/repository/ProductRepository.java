package com.oraclecompany.bbanggle.domain.product.repository;

import com.oraclecompany.bbanggle.domain.product.entity.Product;
import com.oraclecompany.bbanggle.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

    Page<Product> findByStore(Store store, Pageable pageable);

    List<Product> findProductsByStore(Store store);
}
