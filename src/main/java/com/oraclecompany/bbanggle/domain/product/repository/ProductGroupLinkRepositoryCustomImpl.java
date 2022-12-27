package com.oraclecompany.bbanggle.domain.product.repository;

import com.oraclecompany.bbanggle.domain.product.entity.Product;
import com.oraclecompany.bbanggle.domain.product.entity.ProductGroupLink;
import com.oraclecompany.bbanggle.domain.product.entity.QProductTimetable;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.oraclecompany.bbanggle.domain.product.entity.QProductGroupLink.productGroupLink;
import static com.oraclecompany.bbanggle.domain.product.entity.QProductTimetable.productTimetable;

public class ProductGroupLinkRepositoryCustomImpl implements ProductGroupLinkRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public ProductGroupLinkRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ProductGroupLink> findByProduct(Pageable pageable, List<Product> product) {

        List<ProductGroupLink> products = queryFactory
                .select(productGroupLink)
                .from(productGroupLink)
                .join(productGroupLink.product)
                .join(productTimetable).on(productGroupLink.product.id.eq(productTimetable.product.id))
                .where(productGroupLink.product.in(product))
                .orderBy(productGroupLink.seq.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalCount = queryFactory
                .select(productGroupLink.count())
                .from(productGroupLink)
                .fetchOne();

        return new PageImpl<>(products, pageable, totalCount);
    }
}
