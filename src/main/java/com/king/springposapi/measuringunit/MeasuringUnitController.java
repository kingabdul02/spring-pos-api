package com.king.springposapi.measuringunit;

import com.king.springposapi.measuringunit.MeasuringUnitUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("measuring-units")
public class MeasuringUnitController {
    private final MeasuringUnitService measuringUnitService;

    @GetMapping
    public List<MeasuringUnitDTO> list(){
        return measuringUnitService.list();
    }

    @GetMapping("{id}")
    public ResponseEntity<MeasuringUnitDTO> getMeasuringUnit(@PathVariable("id")String id){
        return measuringUnitService.getMeasuringUnit(UUID.fromString(id));
    }

    @PostMapping
    public ResponseEntity<MeasuringUnitDTO> create(@RequestBody MeasuringUnitCreateRequest request){
        return measuringUnitService.create(request);
    }

    @PutMapping("{id}")
    public ResponseEntity<MeasuringUnitDTO> update(@PathVariable("id") UUID id, @RequestBody MeasuringUnitUpdateRequest request){
        return measuringUnitService.update(request, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id")UUID id){
        measuringUnitService.delete(id);
    }
}
