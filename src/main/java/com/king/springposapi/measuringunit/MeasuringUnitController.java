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

    @PostMapping
    public ResponseEntity<?> create(@RequestBody MeasuringUnitCreateRequest request){
        measuringUnitService.create(request);
        return ResponseEntity.ok("Record created successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID id, @RequestBody MeasuringUnitUpdateRequest request){
        measuringUnitService.update(request, id);
        return ResponseEntity.ok("Record updated successfully");
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id")UUID id){
        measuringUnitService.delete(id);
    }
}
