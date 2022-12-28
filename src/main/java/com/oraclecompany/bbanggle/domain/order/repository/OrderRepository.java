package com.oraclecompany.bbanggle.domain.order.repository;

import com.oraclecompany.bbanggle.domain.order.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long>, OrderRepositoryCustom {
}
