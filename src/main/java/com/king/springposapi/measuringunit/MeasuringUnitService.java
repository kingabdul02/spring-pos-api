package com.king.springposapi.measuringunit;

import com.king.springposapi.category.Category;
import com.king.springposapi.category.CategoryUpdateRequest;
import com.king.springposapi.validators.ObjectValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeasuringUnitService {
    private final MeasuringUnitRepository measuringUnitRepository;
    private final MeasuringUnitDTOMapper measuringUnitDTOMapper;

    private final ObjectValidator<MeasuringUnit> objectValidator;

    public List<MeasuringUnitDTO> list(){
        List<MeasuringUnit> measuringUnit = measuringUnitRepository.findAll();
        return measuringUnit
                .stream()
                .map(measuringUnitDTOMapper)
                .collect(Collectors.toList());
    }

    public ResponseEntity<MeasuringUnitDTO> getMeasuringUnit(UUID id){
        var measuringUnit = measuringUnitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Measuring unit not found"));
        return  ResponseEntity.ok(measuringUnitDTOMapper.apply(measuringUnit));
    }

    public ResponseEntity<MeasuringUnitDTO> create(MeasuringUnitCreateRequest request){
        MeasuringUnit measuringUnit = new MeasuringUnit(
                request.label(),
                request.unit(),
                request.symbol()
        );

        objectValidator.validate(measuringUnit);
        measuringUnitRepository.save(measuringUnit);

        return ResponseEntity.ok(measuringUnitDTOMapper.apply(measuringUnit));
    }

    public ResponseEntity<MeasuringUnitDTO> update(MeasuringUnitUpdateRequest request, UUID id){
        var measuringUnit = measuringUnitRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Measuring Unit not found"));
        measuringUnit.setUnit(request.unit());
        measuringUnit.setLabel(request.label());
        measuringUnit.setSymbol(request.symbol());

        objectValidator.validate(measuringUnit);
        measuringUnitRepository.save(measuringUnit);

        return  ResponseEntity.ok(measuringUnitDTOMapper.apply(measuringUnit));
    }

    public void delete(UUID id){
        var measuringUnit = measuringUnitRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Measuring Unit not found"));
        measuringUnitRepository.deleteById(id);
    }
}
