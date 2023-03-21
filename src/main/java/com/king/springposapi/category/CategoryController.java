package com.king.springposapi.category;

import com.king.springposapi.product.ProductUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryCreateRequest request){
        return categoryService.create(request);
    }

    @GetMapping("{id}")
    public CategoryDTO getCategory(@PathVariable("id")String id){
        return categoryService.getCategory(UUID.fromString(id));
    }
    @PutMapping("{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable("id") UUID id, @RequestBody CategoryUpdateRequest request){
        return categoryService.update(request, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id")UUID id){
        categoryService.delete(id);
    }
}
