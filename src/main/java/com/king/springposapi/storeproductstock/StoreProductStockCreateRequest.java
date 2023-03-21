package com.king.springposapi.storeproductstock;

import com.king.springposapi.product.ProductDTO;
import com.king.springposapi.store.StoreDTO;

import java.util.UUID;

public record StoreProductStockCreateRequest(
        Float unitPrice,
        Integer quantity,
        Integer restockTargetLevel,
        Integer  holdingTargetLevel,
        UUID product_id,
        UUID store_id
) {
}
