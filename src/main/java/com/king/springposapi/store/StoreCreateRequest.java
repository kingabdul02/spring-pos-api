package com.king.springposapi.store;

public record StoreCreateRequest(
        String name,
        String location
) {
}
