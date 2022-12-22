package com.oraclecompany.bbanggle.domain.product.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class ProductOptionLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private ProductOptionGroup productOptionGroup;

    @Column(name = "seq", nullable = false)
    private int seq;

    @Builder
    public ProductOptionLink(Product product,
                             ProductOptionGroup productOptionGroup,
                             int seq) {
        this.product = product;
        this.productOptionGroup = productOptionGroup;
        this.seq = seq;
    }
}