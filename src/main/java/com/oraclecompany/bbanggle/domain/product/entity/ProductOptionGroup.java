package com.oraclecompany.bbanggle.domain.product.entity;

import com.oraclecompany.bbanggle.domain.common.BaseEntity;
import com.oraclecompany.bbanggle.domain.common.constant.YesOrNo;
import com.oraclecompany.bbanggle.domain.store.entity.Store;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ProductOptionGroup extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private YesOrNo deleteYn;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private YesOrNo multiCheckYn;

    private int maxCheckCount;

    @OneToMany(mappedBy = "productOptionGroup", cascade = CascadeType.ALL)
    private List<ProductOptionItem> productOptionItems;

    @OneToMany(mappedBy = "productOptionGroup", cascade = CascadeType.ALL)
    private List<ProductOptionLink> productOptionLinks;

    @Builder
    public ProductOptionGroup (Store store,
                               String name,
                               YesOrNo deleteYn,
                               YesOrNo multiCheckYn,
                               int maxCheckCount,
                               List<ProductOptionItem> productOptionItems,
                               List<ProductOptionLink> productOptionLinks) {
        this.store = store;
        this.name = name;
        this.deleteYn = deleteYn;
        this.multiCheckYn = multiCheckYn;
        this.maxCheckCount = maxCheckCount;
        this.productOptionItems = productOptionItems;
        this.productOptionLinks = productOptionLinks;
    }

}
