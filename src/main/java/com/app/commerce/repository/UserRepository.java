package com.app.commerce.repository;

import com.app.commerce.entity.User;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = {"teams", "roles"})
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByStaffId(String staffId);

    @Override
    @EntityGraph(attributePaths = {"teams", "roles"})
    @QueryHints(
            value = @QueryHint(name = "org.hibernate.fetchSize", value = "0")
    )
    Page<User> findAll(Specification<User> spec, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"teams", "roles"})
    Optional<User> findById(Long id);
}
