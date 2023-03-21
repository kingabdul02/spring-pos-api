package com.king.springposapi.orderitem;

import com.king.springposapi.product.ProductDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@RequiredArgsConstructor
@Service
public class OrderItemDTOMapper implements Function<OrderItem, OrderItemResponse> {
    private final ProductDTOMapper productDTOMapper;

    @Override
    public OrderItemResponse apply(OrderItem orderItem) {
        return new OrderItemResponse(
                orderItem.getQuantity(),
                productDTOMapper.apply(orderItem.getProduct())
        );
    }
}
