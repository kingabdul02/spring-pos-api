package com.king.springposapi.orderitem;

import java.util.UUID;

public record OrderItemDTO(
        int quantity,
        UUID product_id
) {
}
