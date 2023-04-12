package com.app.commerce.repository;

import com.app.commerce.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, String> {
}
