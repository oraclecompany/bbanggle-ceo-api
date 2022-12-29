package com.oraclecompany.bbanggle.domain.product.repository;

import com.oraclecompany.bbanggle.domain.product.entity.Product;
import com.oraclecompany.bbanggle.domain.product.entity.QProduct;
import com.oraclecompany.bbanggle.domain.store.entity.Store;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.oraclecompany.bbanggle.domain.product.entity.QProduct.product;
import static com.oraclecompany.bbanggle.domain.product.entity.QProductTimetable.productTimetable;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ProductRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Product> findByProduct(Pageable pageable, Store store) {

        List<Product> products = queryFactory
                .selectFrom(product)
                .join(QProduct.product)
                .join(productTimetable).on(QProduct.product.id.eq(productTimetable.product.id))
                .where(QProduct.product.in(product))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalCount = queryFactory
                .select(product.count())
                .from(product)
                .fetchOne();

        return new PageImpl<>(products, pageable, totalCount);
    }
}
