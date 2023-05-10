package com.app.commerce.repository;

import com.app.commerce.entity.Conversation;
import com.app.commerce.entity.Order;
import com.app.commerce.repository.projections.StatusCountProjection;
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
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    @Query("SELECT DISTINCT(order.progressStep) " +
            "FROM Order order " +
            "WHERE order.progressStep IS NOT NULL")
    List<String> findAllStatuses();


    @Query("SELECT order.progressStep as status, " +
            "COUNT (order) as count " +
            "FROM Order order " +
            "JOIN order.shop shop " +
            "WHERE order.progressStep IS NOT NULL AND (?1 IS NULL OR shop.id = ?1) " +
            "GROUP BY order.progressStep")
    List<StatusCountProjection> findAllStatuses(String shopId);

    @Override
    @EntityGraph(attributePaths = {"shop"})
    Page<Order> findAll(Specification<Order> spec, Pageable pageable);
}
