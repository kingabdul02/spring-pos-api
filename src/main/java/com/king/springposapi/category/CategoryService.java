package com.king.springposapi.category;

import com.king.springposapi.exception.ObjectNotValidException;
import com.king.springposapi.product.ProductUpdateRequest;
import com.king.springposapi.validators.ObjectValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.king.springposapi.exception.GlobalExceptionHandler.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final ObjectValidator<Category> objectValidator;
    private final CategoryRepository categoryRepository;
    private final CategoryDTOMapper categoryDTOMapper;

    public List<CategoryDTO> list(){
        return categoryRepository.findAll()
                .stream()
                .map(categoryDTOMapper)
                .toList();
    }

    public CategoryDTO getCategory(UUID id){
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + id));
        return categoryDTOMapper.apply(category);
    }

    public ResponseEntity<CategoryDTO> create(CategoryCreateRequest categoryCreateRequest){
        Category category = new Category(
                categoryCreateRequest.name(),
                categoryCreateRequest.description()
        );

        objectValidator.validate(category);

        categoryRepository.save(category);
        return ResponseEntity.ok(categoryDTOMapper.apply(category));
    }

    public ResponseEntity<CategoryDTO> update(CategoryUpdateRequest request, UUID id){
        var category = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found"));
        category.setName(request.name());
        category.setDescription(request.description());
        objectValidator.validate(category);
        categoryRepository.save(category);

        return ResponseEntity.ok(categoryDTOMapper.apply(category));
    }

    public void delete(UUID id){
        var category = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found"));
        categoryRepository.deleteById(id);
    }
}
