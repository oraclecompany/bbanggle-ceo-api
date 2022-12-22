package com.oraclecompany.bbanggle.domain.product.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class ProductOptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "deleteYn", nullable = false)
    private char deleteYn;

    @Column(name = "multiCheckYn", nullable = false)
    private char multiCheckYn;

    @Column(name = "maxCheckYn")
    private char maxCheckYn;

    @Builder
    public ProductOptionGroup (String name,
                               char deleteYn,
                               char multiCheckYn,
                               char maxCheckYn) {
        this.name = name;
        this.deleteYn = deleteYn;
        this.multiCheckYn = multiCheckYn;
        this.maxCheckYn = maxCheckYn;
    }
}
