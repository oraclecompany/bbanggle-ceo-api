package com.oraclecompany.bbanggle.domain.product.repository;

import com.oraclecompany.bbanggle.domain.product.constant.DayCode;
import com.oraclecompany.bbanggle.domain.product.entity.Product;
import com.oraclecompany.bbanggle.domain.store.entity.Store;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static com.oraclecompany.bbanggle.domain.product.entity.QProduct.product;
import static com.oraclecompany.bbanggle.domain.product.entity.QProductGroup.productGroup;
import static com.oraclecompany.bbanggle.domain.product.entity.QProductTimetable.productTimetable;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ProductRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Product> findByStore(Store store, Pageable pageable) {
        int dayOfWeek = LocalDate.now().getDayOfWeek().getValue();
        List<DayCode> todayDayCode = DayCode.findDayCode(dayOfWeek);

        List<Product> products = queryFactory
                .selectFrom(product).distinct()
                .leftJoin(product.productTimetables, productTimetable).fetchJoin()
                .leftJoin(product.productGroup, productGroup).fetchJoin()
                .where(
                        product.store.eq(store),
                        productTimetable.dayCode.in(todayDayCode)
                )
                .orderBy(product.productGroup.seq.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalCount = queryFactory
                .select(product.count()).distinct()
                .from(product)
                .leftJoin(product.productTimetables, productTimetable)
                .where(
                        product.store.eq(store),
                        productTimetable.dayCode.in(todayDayCode)
                )
                .fetchOne();

        return new PageImpl<>(products, pageable, totalCount);
    }

}
