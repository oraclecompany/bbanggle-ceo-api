package com.oraclecompany.bbanggle.domain.product.entity;

import com.oraclecompany.bbanggle.domain.common.BaseEntity;
import com.oraclecompany.bbanggle.domain.common.constant.YesOrNo;
import com.oraclecompany.bbanggle.domain.product.constant.SellStatus;
import com.oraclecompany.bbanggle.domain.store.entity.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 자동생성전략 IDENTITY : DB에 위임
    @Column(name = "id")    //객체 필드를 테이블의 컬럼에 매핑, name : 필드와 매핑할 테이블의 컬럼이름을 지정
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column
    private String intro;

    @Column(nullable = false)
    private Long price;

    @Column
    private int quantity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SellStatus status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private YesOrNo hiddenYn;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private YesOrNo deleteYn;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private YesOrNo signatureYn;

    @Column
    private int defaultQuantity;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductTimetable> productTimetables;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_group_id")
    private ProductGroup productGroup;

    @Builder
    public Product(String name,
                   Store store,
                   String intro,
                   Long price,
                   int quantity,
                   SellStatus status,
                   YesOrNo hiddenYn,
                   YesOrNo deleteYn,
                   YesOrNo signatureYn,
                   int defaultQuantity) {
        this.name = name;
        this.store = store;
        this.intro = intro;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.hiddenYn = hiddenYn;
        this.deleteYn = deleteYn;
        this.signatureYn = signatureYn;
        this.defaultQuantity = defaultQuantity;
    }

    public void modifyQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void plusQuantity() {
        this.quantity += 1;
    }

    public void minusQuantity() {
        this.quantity -= 1;
    }

    public void updateStatus(SellStatus status) {
        this.status = status;
    }
}
