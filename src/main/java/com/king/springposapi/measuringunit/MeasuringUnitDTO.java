package com.king.springposapi.measuringunit;

import java.util.UUID;

public record MeasuringUnitDTO(
        UUID id,
        String label,
        String symbol,
        String unit
) {
}
