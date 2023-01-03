package com.oraclecompany.bbanggle.domain.product.repository;

import com.oraclecompany.bbanggle.domain.product.entity.ProductOptionGroup;
import com.oraclecompany.bbanggle.domain.store.entity.Store;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.oraclecompany.bbanggle.domain.product.entity.QProductOptionGroup.productOptionGroup;
import static com.oraclecompany.bbanggle.domain.product.entity.QProductOptionItem.productOptionItem;
import static com.oraclecompany.bbanggle.domain.product.entity.QProductOptionLink.productOptionLink;
import static com.oraclecompany.bbanggle.domain.product.entity.QProduct.product;

public class ProductOptionGroupRepositoryCustomImpl implements ProductOptionGroupRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public ProductOptionGroupRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ProductOptionGroup> findByStore(Pageable pageable, Store store) {

        List<ProductOptionGroup> productGroups = queryFactory
                .selectFrom(productOptionGroup)
                .leftJoin(productOptionGroup.productOptionItems, productOptionItem)
                .leftJoin(productOptionGroup.productOptionLinks, productOptionLink).fetchJoin()
                .leftJoin(productOptionLink.product, product).fetchJoin()
                .where(storeEq(store))
                .groupBy(productOptionGroup.id)
                .orderBy(productOptionGroup.name.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalCount = queryFactory
                .select(productOptionGroup.count())
                .from(productOptionGroup)
                .fetchOne();

        return new PageImpl<>(productGroups, pageable, totalCount);
    }

    @Override
    public ProductOptionGroup findByProductOptionGroup(ProductOptionGroup productOption) {
        return queryFactory
                .selectFrom(productOptionGroup)
                .leftJoin(productOptionGroup.productOptionItems, productOptionItem)
                .leftJoin(productOptionGroup.productOptionLinks, productOptionLink).fetchJoin()
                .leftJoin(productOptionLink.product, product).fetchJoin()
                .where(productOptionGroupEq(productOption))
                .fetchOne();
    }

    private BooleanExpression productOptionGroupEq(ProductOptionGroup productOption) {
        return productOption != null ? productOptionGroup.eq(productOption) : null;
    }

    private BooleanExpression storeEq(Store store) {
        return store != null ? productOptionGroup.store.eq(store) : null;
    }
}
