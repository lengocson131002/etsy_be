package com.app.commerce.repository;

import com.app.commerce.entity.GoLoginProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<GoLoginProfile, String>, JpaSpecificationExecutor<GoLoginProfile> {

    @Override
    @EntityGraph(attributePaths = {"shop"})
    Page<GoLoginProfile> findAll(Specification<GoLoginProfile> spec, Pageable pageable);
}
