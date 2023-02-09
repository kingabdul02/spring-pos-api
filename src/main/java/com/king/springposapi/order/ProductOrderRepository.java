package com.king.springposapi.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, UUID> {
    Optional<ProductOrder> findProductOrderByOrderNo(String orderNo);
}
