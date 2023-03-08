package com.king.springposapi.product;

import java.util.Optional;
import java.util.UUID;

public record ProductUpdateRequest(
        String name,
        Float price,
        UUID category_id,
        UUID measuring_unit_id
) {
}
