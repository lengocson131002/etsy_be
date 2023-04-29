package com.app.commerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = Order.COLLECTION_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@FieldNameConstants
public class Order extends BaseEntity {
    public static final String COLLECTION_NAME = "orders";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String etsyOrderId;
    private String progressStep;
    private Integer itemCount;
    private BigDecimal itemTotal;
    private Double couponRate;
    private BigDecimal couponValue;
    private String couponCode;
    private BigDecimal subTotal;
    private BigDecimal orderTotal;
    private BigDecimal adjustedTotal;
    private BigDecimal tax;
    private String orderName;
    private String orderEmail;
    private OffsetDateTime orderTime;
    private String shippingCustomerName;
    private BigDecimal shippingPrice;
    private String shippingAddress;
    private String shippingBy;
    private String shippingCareer;
    private String estimateDelivery;
    private String trackingNumber;
    private Boolean markAsGift;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "order",
            orphanRemoval = true)
    private List<OrderItem> items;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    public Order setItems(List<OrderItem> items) {
        if (this.items == null) {
            this.items = items;
        } else {
            this.items.clear();
            this.items.addAll(items);
        }
        this.items.forEach(item -> {
            item.setOrder(this);
        });
        return this;
    }
}
