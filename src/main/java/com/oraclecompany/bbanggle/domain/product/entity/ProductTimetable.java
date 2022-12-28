package com.oraclecompany.bbanggle.domain.product.entity;

import com.oraclecompany.bbanggle.domain.common.BaseEntity;
import com.oraclecompany.bbanggle.domain.common.constant.YesOrNo;
import com.oraclecompany.bbanggle.domain.product.constant.DayCode;
import com.oraclecompany.bbanggle.domain.product.constant.DayType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;

@NoArgsConstructor
@Getter
@Entity
public class ProductTimetable extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DayCode dayCode;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private YesOrNo deleteYn;

    private Time hhmm;

    @Enumerated(EnumType.STRING)
    private DayType dayType;

    @Builder
    public ProductTimetable(Product product,
                            DayCode dayCode,
                            Time hhmm,
                            DayType dayType,
                            YesOrNo deleteYn) {
        this.product = product;
        this.dayCode = dayCode;
        this.hhmm = hhmm;
        this.dayType = dayType;
        this.deleteYn = deleteYn;
    }
}
