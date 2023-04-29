package com.app.commerce.repository;

import com.app.commerce.entity.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>, JpaSpecificationExecutor<Team> {

    boolean existsByName(String name);

    boolean existsByCode(String code);

    @Override
    @EntityGraph(attributePaths = { "shops" , "staffs"})
    Page<Team> findAll(Specification<Team> spec, Pageable pageable);
}