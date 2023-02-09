package com.king.springposapi.product;

import com.king.springposapi.category.Category;
import com.king.springposapi.category.CategoryDTO;
import com.king.springposapi.measuringunit.MeasuringUnit;
import com.king.springposapi.measuringunit.MeasuringUnitDTO;

import java.util.UUID;

public record ProductDTO(
        UUID id,
        String name,
        Float price,
        CategoryDTO category,
        MeasuringUnitDTO measuringUnit
) {
}
