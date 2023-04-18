package com.app.commerce.repository;

import com.app.commerce.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Listing, Long>, JpaSpecificationExecutor<Listing> {
}
