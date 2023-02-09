package com.king.springposapi.measuringunit;

public record MeasuringUnitCreateRequest(
        String label,
        String symbol,
        String unit
) {
}
