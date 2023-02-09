package com.king.springposapi.order;

import com.king.springposapi.orderitem.OrderItem;
import com.king.springposapi.orderitem.OrderItemDTO;
import com.king.springposapi.paymententry.PaymentMethod;

import java.util.List;
import java.util.UUID;

public record ProductOrderCreateRequest(
        String orderNo,
        OrderStatus status,
        Boolean isPaid,
        UUID payment_entry_id,
        UUID user_id,
        Float amount,
        Float total,
        PaymentMethod paymentMethod,
        List<OrderItemDTO> orderItems
) {
}
