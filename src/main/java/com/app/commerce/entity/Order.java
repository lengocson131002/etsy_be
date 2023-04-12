package com.app.commerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = Order.COLLECTION_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Order extends BaseEntity {
    public static final String COLLECTION_NAME = "orders";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private BigDecimal discount;
    private String orderName;
    private String orderPhone;
    private String orderEmail;
    private String orderCountry;
    private String orderState;
    private String orderCity;
    private String orderAddress;
    private String status;
    private String trackingNumber;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> items;

    @ManyToOne(cascade = CascadeType.ALL)
    private Shop shop;
}
