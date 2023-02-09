package com.king.springposapi.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductDTO> list(){
        return productService.list();
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductCreateRequest request){
        return ResponseEntity.ok(productService.create(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID id, @RequestBody ProductUpdateRequest request){
        productService.update(request, id);
        return ResponseEntity.ok("Record updated successfully");
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id")UUID id){
        productService.delete(id);
    }
}
