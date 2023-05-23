package com.app.commerce.repository;

import com.app.commerce.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByEmail(String email);

    @Query("SELECT DISTINCT user " +
            "FROM User user " +
            "LEFT JOIN FETCH user.teams teams " +
            "LEFT JOIN FETCH user.roles roles " +
            "WHERE user.username = ?1 ")
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByStaffId(String staffId);

    @Override
    @Query(value = "SELECT user " +
            "FROM User user " +
            "LEFT JOIN user.teams teams " +
            "LEFT JOIN user.roles roles")
    Page<User> findAll(Specification<User> spec, Pageable pageable);

    @Override
    @Query("SELECT user " +
            "FROM User user " +
            "LEFT JOIN FETCH user.teams teams " +
            "LEFT JOIN FETCH user.roles roles " +
            "WHERE user.id = ?1 ")
    Optional<User> findById(Long id);
}
