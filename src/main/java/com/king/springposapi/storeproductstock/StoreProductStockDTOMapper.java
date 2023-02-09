package com.king.springposapi.storeproductstock;

import com.king.springposapi.product.ProductDTOMapper;
import com.king.springposapi.store.StoreDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class StoreProductStockDTOMapper implements Function<StoreProductStock, StoreProductStockDTO> {
    private final ProductDTOMapper productDTOMapper;
    private final StoreDTOMapper storeDTOMapper;
    @Override
    public StoreProductStockDTO apply(StoreProductStock storeProductStock) {
        return new StoreProductStockDTO(
                storeProductStock.getId(),
                storeProductStock.getUnitPrice(),
                storeProductStock.getQuantity(),
                storeProductStock.getRestockTargetLevel(),
                storeProductStock.getHoldingTargetLevel(),
                productDTOMapper.apply(storeProductStock.getProduct()),
                storeDTOMapper.apply(storeProductStock.getStore())
        );
    }
}
