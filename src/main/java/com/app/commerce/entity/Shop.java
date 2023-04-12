package com.app.commerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Entity
@Table(name = Shop.COLLECTION_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Shop extends BaseEntity {

    public static final String COLLECTION_NAME = "shops";

    @Id
//    @GenericGenerator(name = "uuid2", strategy = "uuid2")
//    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
//    @Column(length = 36, nullable = false, updatable = false)
    private String id;
    private String name;
    private String slug;
    private String logo;
    private String websiteUrl;
    private String address;
    private String description;
    private boolean isActive;
    private String profileId;
    private String profileName;

    @OneToOne(mappedBy = "shop")
    private Dashboard todayDashboard;

    @OneToOne(mappedBy = "shop")
    private Dashboard yesterdayDashboard;

    @OneToOne(mappedBy = "shop")
    private Dashboard last7Dashboard;

    @OneToOne(mappedBy = "shop")
    private Dashboard last30Dashboard;

    @OneToOne(mappedBy = "shop")
    private Dashboard thisMonthDashboard;


    @OneToMany(
            mappedBy = "shop",
            orphanRemoval = true
    )
    private List<Product> listings;

    @OneToMany(
            mappedBy = "shop",
            orphanRemoval = true
    )
    private List<Order> orders;

}
