package com.oraclecompany.bbanggle.domain.product.entity;

import com.oraclecompany.bbanggle.domain.common.BaseEntity;
import com.oraclecompany.bbanggle.domain.common.constant.YesOrNo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class ProductOptionGroup extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private YesOrNo deleteYn;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private YesOrNo multiCheckYn;

    private int maxCheckCount;

    @Builder
    public ProductOptionGroup (String name,
                               YesOrNo deleteYn,
                               YesOrNo multiCheckYn,
                               int maxCheckCount) {
        this.name = name;
        this.deleteYn = deleteYn;
        this.multiCheckYn = multiCheckYn;
        this.maxCheckCount = maxCheckCount;
    }
}
