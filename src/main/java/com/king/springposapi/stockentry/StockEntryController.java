package com.king.springposapi.stockentry;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stock-entries")
public class StockEntryController {
    private final StockEntryService stockEntryService;

    @GetMapping
    public ResponseEntity<List<StockEntryDTO>> list(){
        return ResponseEntity.ok(stockEntryService.list());
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<StockEntryDTO>> getStockEntry(@PathVariable("id")UUID id){
        return ResponseEntity.ok(stockEntryService.getStockEntry(id));
    }

    @PostMapping
    public ResponseEntity<StockEntry> create(@RequestBody StockEntryCreateRequest request){
        return ResponseEntity.ok(stockEntryService.create(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestBody StockEntryUpdateRequest request, @PathVariable("id")UUID id){
        stockEntryService.update(request, id);
        return ResponseEntity.ok("Record updated successfully");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id")UUID id){
        stockEntryService.delete(id);
        return ResponseEntity.ok("Record deleted successfully");
    }
}
