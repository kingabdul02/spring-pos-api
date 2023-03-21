package com.king.springposapi.stockentry;

public record StockEntryUpdateRequest(
        StockType type,
        StockEntryStatus status,
        Integer quantity_supplied,
        Float unit_price
) {
}
