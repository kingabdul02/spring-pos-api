package com.king.springposapi.stockentry;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockEntryRepository extends JpaRepository<StockEntry, UUID> {
}