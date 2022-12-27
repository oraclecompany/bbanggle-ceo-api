package com.oraclecompany.bbanggle.domain.store.entity;

import com.oraclecompany.bbanggle.domain.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String intro;

    @Column
    private String notice;

    @Builder
    private Store(String name) {
        this.name = name;
    }
}
