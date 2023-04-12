package com.app.commerce.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = Dashboard.COLLECTION_NAME)
public class Dashboard {

    public static final String COLLECTION_NAME = "dashboard";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer visits;

    private Integer orders;

    private Double conversionRate;

    private BigDecimal revenue;

    @OneToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

}
