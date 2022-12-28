package com.oraclecompany.bbanggle.domain.order.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public OrderRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

//    @Override
//    public Page<Orders> findOrderPage(Pageable pageable, Member member) {
//        List<Orders> contents = queryFactory
//                .selectFrom(orders)
//                .where(memberEq(member))
//                .orderBy(orders.orderTime.desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//
//        Long totalCount = queryFactory
//                .select(orders.count())
//                .from(orders)
//                .where(memberEq(member))
//                .fetchOne();
//
//        return new PageImpl<>(contents, pageable, totalCount);
//    }
//
//    private BooleanExpression memberEq(Member member) {
//        return member != null ? orders.member.eq(member) : null;
//    }
}
