package com.king.springposapi.storeproductstock;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StoreProductStockRepository extends JpaRepository<StoreProductStock, UUID> {
}
