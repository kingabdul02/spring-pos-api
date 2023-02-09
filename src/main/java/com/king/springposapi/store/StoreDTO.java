package com.king.springposapi.store;

import java.util.UUID;

public record StoreDTO(
        UUID id,
        String name,
        String location,
        StoreStatus status
) {
}
