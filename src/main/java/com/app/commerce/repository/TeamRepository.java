package com.app.commerce.repository;

import com.app.commerce.entity.Team;
import jakarta.persistence.QueryHint;
import org.hibernate.jpa.HibernateHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


@Repository
public interface TeamRepository extends JpaRepository<Team, Long>, JpaSpecificationExecutor<Team> {

    boolean existsByName(String name);

    boolean existsByCode(String code);

    @Override
//    @EntityGraph(value = "team.fetch", type = EntityGraph.EntityGraphType.FETCH)
    Page<Team> findAll(Specification<Team> spec, Pageable pageable);
}
