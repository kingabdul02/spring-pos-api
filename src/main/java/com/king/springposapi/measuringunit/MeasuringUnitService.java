package com.king.springposapi.measuringunit;

import com.king.springposapi.category.CategoryUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeasuringUnitService {
    private final MeasuringUnitRepository measuringUnitRepository;
    private final MeasuringUnitDTOMapper measuringUnitDTOMapper;

    public List<MeasuringUnitDTO> list(){
        List<MeasuringUnit> measuringUnit = measuringUnitRepository.findAll();
        return measuringUnit
                .stream()
                .map(measuringUnitDTOMapper)
                .collect(Collectors.toList());
    }

    public void create(MeasuringUnitCreateRequest request){
        MeasuringUnit measuringUnit = new MeasuringUnit(
                request.label(),
                request.unit(),
                request.symbol()
        );
        measuringUnitRepository.save(measuringUnit);
    }

    public void update(MeasuringUnitUpdateRequest request, UUID id){
        var measuringUnit = measuringUnitRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Measuring Unit not found"));
//        update record
    }

    public void delete(UUID id){
        var measuringUnit = measuringUnitRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Measuring Unit not found"));
        measuringUnitRepository.deleteById(id);
    }
}
