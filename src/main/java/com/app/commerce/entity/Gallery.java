package com.app.commerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

@Entity
@Table(name = Gallery.COLLECTION_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Gallery extends BaseEntity {

    public static final String COLLECTION_NAME = "galleries";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String slug;
    private String url;
    private Integer displayOrder;
    private String code;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

}
