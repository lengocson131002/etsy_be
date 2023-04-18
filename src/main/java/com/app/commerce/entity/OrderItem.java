package com.app.commerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Entity
@Table(name = OrderItem.COLLECTION_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OrderItem {

    public final static String COLLECTION_NAME = "order_items";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String etsyListingId;
    private String image;
    private BigDecimal price;
    private String name;
    private Integer quantity;
    private String description;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
