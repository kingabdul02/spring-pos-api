package com.king.springposapi.measuringunit;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class MeasuringUnitDTOMapper implements Function<MeasuringUnit, MeasuringUnitDTO> {
    @Override
    public MeasuringUnitDTO apply(MeasuringUnit measuringUnit) {
        System.out.println(measuringUnit.getUnit());
        System.out.println(measuringUnit.getLabel());
        return new MeasuringUnitDTO(
                measuringUnit.getId(),
                measuringUnit.getLabel(),
                measuringUnit.getUnit(),
                measuringUnit.getSymbol()
        );
    }
}
