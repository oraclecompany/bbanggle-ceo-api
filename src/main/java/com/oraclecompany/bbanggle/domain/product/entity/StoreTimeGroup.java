package com.oraclecompany.bbanggle.domain.product.entity;

import com.oraclecompany.bbanggle.domain.common.BaseEntity;
import com.oraclecompany.bbanggle.domain.common.constant.YesOrNo;
import com.oraclecompany.bbanggle.domain.store.entity.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class StoreTimeGroup extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int seq;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private YesOrNo openYn;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private YesOrNo deleteYn;

    @Builder
    public StoreTimeGroup(Store store,
                          String name,
                          int seq,
                          YesOrNo openYn,
                          YesOrNo deleteYn) {
        this.store = store;
        this.name = name;
        this.seq = seq;
        this.openYn = openYn;
        this.deleteYn = deleteYn;
    }
}
