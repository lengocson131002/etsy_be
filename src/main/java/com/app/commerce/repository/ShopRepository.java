package com.app.commerce.repository;

import com.app.commerce.entity.Shop;
import com.app.commerce.repository.projections.DashboardShopStatusCountProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, String>, JpaSpecificationExecutor<Shop> {

    @Query("SELECT shop.status as status," +
            "COUNT (shop) as count " +
            "FROM Shop shop " +
            "GROUP BY shop.status"
    )
    List<DashboardShopStatusCountProjection> countShopStatus();

}
