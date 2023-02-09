package com.king.springposapi.storeproductstock;

import com.king.springposapi.product.ProductDTO;
import com.king.springposapi.store.StoreDTO;

import java.util.UUID;

public record StoreProductStockDTO(
        UUID id,
        Float unitPrice,
        Integer quantity,
        Integer restockTargetLevel,
        Integer  holdingTargetLevel,
        ProductDTO product,
        StoreDTO store
) {
}
