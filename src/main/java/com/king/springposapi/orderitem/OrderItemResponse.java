package com.king.springposapi.orderitem;

import com.king.springposapi.product.ProductDTO;

import java.util.UUID;

public record OrderItemResponse(
        int quantity,
        ProductDTO product
) {
}
