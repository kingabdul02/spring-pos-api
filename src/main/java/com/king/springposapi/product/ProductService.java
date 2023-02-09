package com.king.springposapi.product;

import com.king.springposapi.category.CategoryRepository;
import com.king.springposapi.measuringunit.MeasuringUnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductDTOMapper productDTOMapper;
    private final MeasuringUnitRepository measuringUnitRepository;
    private final CategoryRepository categoryRepository;


    public List<ProductDTO> list(){
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productDTOMapper)
                .toList();
    }

    public Product create(ProductCreateRequest request){
        var category = categoryRepository.findById(request.category_id()).orElseThrow(() -> new IllegalArgumentException("Category not found!"));
        var measuringUnit = measuringUnitRepository.findById(request.measuring_unit_id()).orElseThrow(() -> new IllegalArgumentException("Measuring unit not found!"));
        Product product = new Product(
                request.name(),
                request.price(),
                category,
                measuringUnit
        );
        return productRepository.save(product);
    }

    public void update(ProductUpdateRequest request, UUID id){
        var product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
//        update record
    }

    public void delete(UUID id){
        var product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        productRepository.deleteById(id);
    }

}
