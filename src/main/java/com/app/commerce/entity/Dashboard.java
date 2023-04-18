package com.app.commerce.entity;

import com.app.commerce.enums.DashboardType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Entity
@Table(name = Dashboard.COLLECTION_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Dashboard extends BaseEntity {

    public static final String COLLECTION_NAME = "dashboards";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private DashboardType dateRange;
    private Integer visits;

    private Integer orders;

    private Double conversionRate;

    private BigDecimal revenue;
}
