package com.king.springposapi.stockentry;

import java.util.UUID;

public record StockEntryCreateRequest(
        StockType type,
        StockEntryStatus status,
        Integer quantity_supplied,
        Float unit_price,
        UUID store_product_stock_id
) {
}
