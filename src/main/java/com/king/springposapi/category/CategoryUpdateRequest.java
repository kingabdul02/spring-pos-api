package com.king.springposapi.category;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CategoryUpdateRequest(
        @NotNull
        @NotEmpty
        String name,
        String description
) {
}
