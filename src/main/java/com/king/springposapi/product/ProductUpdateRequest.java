package com.king.springposapi.product;

import java.util.Optional;
import java.util.UUID;

public record ProductUpdateRequest(
        Optional<String> name,
        Optional<Float> price,
        Optional<UUID> category_id,
        Optional<UUID> measuring_unit_id
) {
}
