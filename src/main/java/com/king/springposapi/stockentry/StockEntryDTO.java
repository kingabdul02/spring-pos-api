package com.king.springposapi.stockentry;

import com.king.springposapi.storeproductstock.StoreProductStockDTO;

import java.util.UUID;

public record StockEntryDTO(
        UUID id,
        StockType type,
        StockEntryStatus status,
        Integer quantity_supplied,
        Float unit_price,
        StoreProductStockDTO storeProductStock
) {
}
