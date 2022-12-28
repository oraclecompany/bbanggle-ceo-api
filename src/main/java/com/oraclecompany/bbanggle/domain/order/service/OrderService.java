package com.oraclecompany.bbanggle.domain.order.service;

import com.oraclecompany.bbanggle.domain.order.entity.Orders;
import com.oraclecompany.bbanggle.domain.order.repository.OrderRepository;
import com.oraclecompany.bbanggle.global.error.exception.EntityNotFoundException;
import com.oraclecompany.bbanggle.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public Orders findByOrderId(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_EXIST_ORDER));
    }

    @Transactional
    public Orders saveOrders(Orders orders) {
        return orderRepository.save(orders);
    }
}
