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
public class ProductGroupLink extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_group_id")
    private ProductGroup productGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private int seq;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private YesOrNo deleteYn;

    @Builder
    private ProductGroupLink(ProductGroup productGroup,
                             Product product,
                             int seq,
                             YesOrNo deleteYn) {
        this.productGroup = productGroup;
        this.product = product;
        this.seq = seq;
        this.deleteYn = deleteYn;
    }
}
