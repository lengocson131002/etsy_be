package com.app.commerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class ProductOption extends BaseEntity {

    public final static String COLLECTION_NAME = "product_options";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String option;
    private String value;
    private BigDecimal price;
    private Integer quantity;
    private String description;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
