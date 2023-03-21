package com.king.springposapi.measuringunit;

public record MeasuringUnitUpdateRequest(
        String label,
        String symbol,
        String unit
) {
}
