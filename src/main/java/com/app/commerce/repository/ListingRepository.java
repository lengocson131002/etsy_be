package com.app.commerce.repository;

import com.app.commerce.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long>, JpaSpecificationExecutor<Listing> {

    @Query("SELECT DISTINCT(listing.status) " +
            "FROM Listing listing " +
            "WHERE listing.status IS NOT NULL")
    List<String> findAllStatuses();
}
