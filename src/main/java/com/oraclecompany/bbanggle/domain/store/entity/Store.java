package com.oraclecompany.bbanggle.domain.store.entity;


import com.oraclecompany.bbanggle.domain.common.BaseEntity;
import com.oraclecompany.bbanggle.domain.store.constant.StoreStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(length = 20)
    private String name;

    @Column(length = 50)
    private String intro;

    @Column
    private String notice;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StoreStatus storeStatus;

    @Column
    private String address;

    @Column(precision = 15, scale = 6)
    private BigDecimal lat;

    @Column(precision = 15, scale = 6)
    private BigDecimal lng;

    @Column
    private String bizHour;

    @Column(length = 15)
    private String tel;

    @Column(length = 100)
    private String ceoComment;

    @Column(length = 100)
    private String countryOrigin;

    @Column
    private Time cancellableTime;

    @Builder
    public Store(String name,
                 String intro,
                 String notice,
                 StoreStatus storeStatus,
                 String address,
                 BigDecimal lat,
                 BigDecimal lng,
                 String bizHour,
                 String tel,
                 String ceoComment,
                 String countryOrigin,
                 Time cancellableTime) {
        this.name = name;
        this.intro = intro;
        this.notice = notice;
        this.storeStatus = storeStatus;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.bizHour = bizHour;
        this.tel = tel;
        this.ceoComment = ceoComment;
        this.countryOrigin = countryOrigin;
        this.cancellableTime = cancellableTime;
    }

    public void modifyStatus(StoreStatus storeStatus) {
        this.storeStatus = storeStatus;
    }
}