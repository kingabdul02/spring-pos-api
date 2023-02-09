package com.king.springposapi.category;

import com.king.springposapi.product.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDTO> list(){
        return categoryService.list();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CategoryCreateRequest request){
        categoryService.create(request);
        return ResponseEntity.ok("Record created successfully");
    }


    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID id, @RequestBody CategoryUpdateRequest request){
        categoryService.update(request, id);
        return ResponseEntity.ok("Record updated successfully");
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id")UUID id){
        categoryService.delete(id);
    }
}
