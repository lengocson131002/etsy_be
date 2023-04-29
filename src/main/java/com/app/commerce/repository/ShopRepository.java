package com.app.commerce.repository;

import com.app.commerce.entity.Shop;
import com.app.commerce.repository.projections.DashboardShopStatusCountProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
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

    @Override
    @EntityGraph(attributePaths = {"trackers", "team"})
    Page<Shop> findAll(Specification<Shop> specification, Pageable pageable);

    @Query("SELECT distinct (shop.status) " +
            "FROM Shop shop " +
            "WHERE shop.status IS NOT NULL")
    List<String> findAllStatuses();

    List<Shop> findByTrackersId(Long trackerId);
}
