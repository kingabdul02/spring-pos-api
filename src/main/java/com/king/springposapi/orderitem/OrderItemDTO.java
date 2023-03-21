package com.king.springposapi.orderitem;

import com.king.springposapi.product.ProductDTO;

import java.util.UUID;

public record OrderItemDTO(
        int quantity,
        UUID product_id
//        ProductDTO product
) {
}
