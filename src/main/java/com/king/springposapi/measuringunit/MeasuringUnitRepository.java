package com.king.springposapi.measuringunit;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MeasuringUnitRepository extends JpaRepository<MeasuringUnit, UUID> {
    Optional<MeasuringUnit> findMeasuringUnitByUnit(String unit);
}
