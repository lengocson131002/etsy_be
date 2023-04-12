package com.app.commerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = Product.COLLECTION_NAME)
public class Product extends BaseEntity{

    public static final String COLLECTION_NAME = "products";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String slug;
    private String brand;
    private String image;
    private BigDecimal regularPrice;
    private BigDecimal discountPrice;
    private String code;
    private String unit;
    private String shortDescription;
    private String description;
    private String categoryId;
    private String categoryName;
    private String subCategoryId;
    private String subCategoryName;
    private Integer inventoryQuantity;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @OneToMany(mappedBy = "product")
    private List<ProductOption> options;

    @OneToMany(mappedBy = "product")
    private List<Gallery> galleries;
}
