package com.king.springposapi.store;

public record StoreUpdateRequest(
        String name,
        String location,
        StoreStatus status
) {
}
