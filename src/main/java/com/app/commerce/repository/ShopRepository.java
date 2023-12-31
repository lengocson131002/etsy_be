package com.app.commerce.repository;

import com.app.commerce.entity.Shop;
import com.app.commerce.repository.projections.StatusCountProjection;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, String>, JpaSpecificationExecutor<Shop> {

    @Query("SELECT shop.status as status," +
            "COUNT (shop) as count " +
            "FROM Shop shop " +
            "GROUP BY shop.status"
    )
    List<StatusCountProjection> countShopStatus();

    @Query("SELECT shop.status as status," +
            "COUNT (shop) as count " +
            "FROM Shop shop " +
            "WHERE ?1 IS NULL OR shop.status = ?1 " +
            "GROUP BY shop.status"
    )
    List<StatusCountProjection> countShopStatus(String status);

    @Override
    @EntityGraph(attributePaths = {"profile", "allTimeDashboard"})
    Page<Shop> findAll(Specification<Shop> specification, Pageable pageable);

    @Query("SELECT distinct (shop.status) " +
            "FROM Shop shop " +
            "WHERE shop.status IS NOT NULL")
    List<String> findAllStatuses();

    List<Shop> findByTrackersId(Long trackerId);

    long countByStatus(String status);
}
