package com.oraclecompany.bbanggle.domain.product.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 자동생성전략 IDENTITY : DB에 위임
    @Column(name = "id")    //객체 필드를 테이블의 컬럼에 매핑, name : 필드와 매핑할 테이블의 컬럼이름을 지정
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "intro")
    private String intro;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "hiddenYn", nullable = false)
    private char hiddenYn;

    @Column(name = "deleteYn", nullable = false)
    private char deleteYn;

    @Column(name = "signatureYn", nullable = false)
    private char signatureYn;

    @Column(name = "defaultQuantity")
    private int defaultQuantity;

    @Builder
    public Product(String name,
                   String intro,
                   Long price,
                   int quantity,
                   String status,
                   char hiddenYn,
                   char deleteYn,
                   char signatureYn,
                   int defaultQuantity) {
        this.name = name;
        this.intro = intro;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.hiddenYn = hiddenYn;
        this.deleteYn = deleteYn;
        this.signatureYn = signatureYn;
        this.defaultQuantity = defaultQuantity;
    }
}
