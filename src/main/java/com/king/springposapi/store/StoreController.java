package com.king.springposapi.store;

import com.king.springposapi.category.CategoryUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {
    private final StoreService storeService;

    @GetMapping
    public ResponseEntity<List<StoreDTO>> list(){
        return ResponseEntity.ok(storeService.list());
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<StoreDTO>> getStore(@PathVariable("id")UUID id){
        return ResponseEntity.ok(storeService.getStore(id));
    }

    @PostMapping
    public Store create(@RequestBody StoreCreateRequest request){
        return storeService.create(request);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID id, @RequestBody StoreUpdateRequest request){
        storeService.update(request, id);
        return ResponseEntity.ok("Record updated successfully");
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id")UUID id){
        storeService.delete(id);
    }
}
