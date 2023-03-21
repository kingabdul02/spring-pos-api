package com.king.springposapi.product;

import com.king.springposapi.category.CategoryDTOMapper;
import com.king.springposapi.measuringunit.MeasuringUnitDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ProductDTOMapper implements Function<Product, ProductDTO> {
    private final CategoryDTOMapper categoryDTOMapper;
    private final MeasuringUnitDTOMapper measuringUnitDTOMapper;

    @Override
    public ProductDTO apply(Product product) {
        return  new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                categoryDTOMapper.apply(product.getCategory()),
                measuringUnitDTOMapper.apply(product.getMeasuringUnit())
        );
    }
}
