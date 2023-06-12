package com.app.commerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.OffsetDateTime;
import java.util.*;

@Entity
@Table(name = Shop.COLLECTION_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@FieldNameConstants
public class Shop extends BaseEntity {

    public static final String COLLECTION_NAME = "shops";

    @Id
    private String id;
    private String name;
    private String previousStatus;
    private String status;
    private String currencySymbol;
    private String currencyCode;
    private OffsetDateTime openedDate;
    private String description;
    private boolean isTracked = false;
    private OffsetDateTime lastSyncAt;
    private String avatar;
    private String banner;

    @ManyToMany
    @JoinTable(
            name = "shop_team",
            joinColumns = @JoinColumn(name = "shop_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 50)
    private Set<Team> teams;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = @JoinColumn(name = "shop_id"),
            inverseJoinColumns = @JoinColumn(name = "staff_id")
    )
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 50)
    private Set<User> trackers;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private GoLoginProfile profile;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "today_dashboard_id")
    private Dashboard todayDashboard;

    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "yesterday_dashboard_id")
    private Dashboard yesterdayDashboard;

    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "last_7_dashboard_id")
    private Dashboard last7Dashboard;

    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "last_30_dashboard_id")
    private Dashboard last30Dashboard;

    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "this_month_dashboard_id")
    private Dashboard thisMonthDashboard;

    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "this_year_dashboard_id")
    private Dashboard thisYearDashboard;

    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "last_year_dashboard_id")
    private Dashboard lastYearDashboard;

    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "all_time_dashboard_id")
    private Dashboard allTimeDashboard;

    @OneToMany(
            mappedBy = "shop",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<Listing> listings;

    @OneToMany(
            mappedBy = "shop",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<Order> orders;

    @OneToMany(
            mappedBy = "shop",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<Conversation> conversations;

    public Shop setListings(List<Listing> products) {
        if (this.listings == null) {
            this.listings = products;
        } else {
            this.listings.clear();
            this.listings.addAll(products);
        }
        this.listings.forEach(product -> {
            product.setShop(this);
        });

        return this;
    }

    public Shop setOrders(List<Order> orders) {
        if (this.orders == null) {
            this.orders = orders;
        } else {
            this.orders.clear();
            this.orders.addAll(orders);
        }
        this.orders.forEach(order -> {
            order.setShop(this);
        });
        return this;
    }

    public Shop setConversations(List<Conversation> conversations) {
        if (this.conversations == null) {
            this.conversations = conversations;
        } else {
            this.conversations.clear();
            this.conversations.addAll(conversations);
        }
        this.conversations.forEach(con -> {
            con.setShop(this);
        });

        return this;
    }

    public Shop setProfile(GoLoginProfile profile) {
        this.profile = profile;
        return this;
    }

    public void addTracker(User staff) {
        if (this.getTrackers() == null) {
            this.setTrackers(new HashSet<>());
        }
        if (this.getTrackers().stream().noneMatch(tracker -> Objects.equals(tracker.getId(), staff.getId()))) {
            this.getTrackers().add(staff);
        }
        this.setTracked(true);
    }

    public void removeTracker(User staff) {
        if (this.getTrackers() == null) {
            return;
        }

        this.trackers.removeIf(tracker -> Objects.equals(tracker.getId(), staff.getId()));

        if (this.getTrackers().isEmpty()) {
            this.setTracked(false);
        }

    }
}
