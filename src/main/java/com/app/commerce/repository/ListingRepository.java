package com.app.commerce.repository;

import com.app.commerce.entity.Conversation;
import com.app.commerce.entity.Listing;
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
public interface ListingRepository extends JpaRepository<Listing, Long>, JpaSpecificationExecutor<Listing> {

    @Query("SELECT DISTINCT(listing.status) " +
            "FROM Listing listing " +
            "WHERE listing.status IS NOT NULL")
    List<String> findAllStatuses();

    @Override
    @EntityGraph(attributePaths = {"shop"})
    Page<Listing> findAll(Specification<Listing> spec, Pageable pageable);
}
