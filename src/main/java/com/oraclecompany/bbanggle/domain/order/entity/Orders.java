package com.oraclecompany.bbanggle.domain.order.entity;

import com.oraclecompany.bbanggle.domain.common.BaseEntity;
import com.oraclecompany.bbanggle.domain.order.constant.OrderStatus;
import com.oraclecompany.bbanggle.domain.order.constant.PayMethod;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Orders extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus orderStatus;

    @Column(length = 20)
    private String tel;

    @Column(length = 50)
    private String inquiry;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private PayMethod payMethod;

    @Column(length = 50)
    private String receiveMethod;

    @Column(length = 15)
    private String orderNo;

    private Long orderPrice;

    private Long paidPrice;

    @Column(name = "order_time", nullable = false, updatable = false)
    private LocalDateTime orderTime;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id", nullable = false)
//    private Member member;
}
