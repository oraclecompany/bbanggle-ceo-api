package com.oraclecompany.bbanggle.domain.product.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class ProductOptionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_option_group_id")
    private ProductOptionGroup productOptionGroup;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "seq", nullable = false)
    private int seq;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "hiddenYn", nullable = false)
    private char hiddenYn;

    @Column(name = "deleteYn", nullable = false)
    private char deleteYn;

    @Builder
    public ProductOptionItem (ProductOptionGroup productOptionGroup,
                              String name,
                              Long price,
                              int seq,
                              String status,
                              char hiddenYn,
                              char deleteYn) {
        this.productOptionGroup = productOptionGroup;
        this.name = name;
        this.price = price;
        this.seq = seq;
        this.status = status;
        this.hiddenYn = hiddenYn;
        this.deleteYn = deleteYn;
    }
}
