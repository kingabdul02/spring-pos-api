package com.king.springposapi.category;

import com.king.springposapi.product.ProductUpdateRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryDTOMapper categoryDTOMapper;

    public List<CategoryDTO> list(){
        return categoryRepository.findAll()
                .stream()
                .map(categoryDTOMapper)
                .toList();
    }

    public void create(CategoryCreateRequest categoryCreateRequest){
        Category category = new Category(
                categoryCreateRequest.name(),
                categoryCreateRequest.description()
        );

        categoryRepository.save(category);
    }

    public void update(CategoryUpdateRequest request, UUID id){
        var category = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found"));
//        update record
    }

    public void delete(UUID id){
        var category = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found"));
        categoryRepository.deleteById(id);
    }
}
