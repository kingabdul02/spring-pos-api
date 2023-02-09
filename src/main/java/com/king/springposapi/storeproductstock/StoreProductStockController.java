package com.king.springposapi.storeproductstock;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store-product-stocks")
public class StoreProductStockController {
    private final StoreProductStockService storeProductStockService;

    @GetMapping
    public ResponseEntity<List<StoreProductStockDTO>> lis(){
        return ResponseEntity.ok(storeProductStockService.list());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getStoreProductStock(@PathVariable("id")UUID id){
        return ResponseEntity.ok(storeProductStockService.getProductStoreStock(id));
    }

    @PostMapping
    public ResponseEntity<StoreProductStock> create(@RequestBody StoreProductStockCreateRequest request){
        return ResponseEntity.ok(storeProductStockService.create(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID id, @RequestBody StoreProductStockUpdateRequest request){
        storeProductStockService.update(request, id);
        return ResponseEntity.ok("Record successfully updated");
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id")UUID id){
        storeProductStockService.delete(id);
    }

}
