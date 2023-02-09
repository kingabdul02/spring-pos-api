package com.king.springposapi.category;

import com.king.springposapi.product.Product;

import java.util.List;
import java.util.UUID;

public record CategoryDTO(
        UUID id,
        String name,
        String description
) {
}
