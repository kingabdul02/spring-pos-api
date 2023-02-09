package com.king.springposapi.product;

import java.util.UUID;

public record ProductCreateRequest(
        String name,
        Float price,
        UUID category_id,
        UUID measuring_unit_id
) {
}
