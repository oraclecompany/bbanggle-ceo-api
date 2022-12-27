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
public class ProductOptionItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_option_group_id")
    private ProductOptionGroup productOptionGroup;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private int seq;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private YesOrNo hiddenYn;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private YesOrNo deleteYn;

    @Builder
    public ProductOptionItem (ProductOptionGroup productOptionGroup,
                              String name,
                              Long price,
                              int seq,
                              String status,
                              YesOrNo hiddenYn,
                              YesOrNo deleteYn) {
        this.productOptionGroup = productOptionGroup;
        this.name = name;
        this.price = price;
        this.seq = seq;
        this.status = status;
        this.hiddenYn = hiddenYn;
        this.deleteYn = deleteYn;
    }
}
