package com.king.springposapi.order;

import com.king.springposapi.orderitem.OrderItem;
import com.king.springposapi.orderitem.OrderItemDTO;
import com.king.springposapi.paymententry.PaymentEntryDTO;
import com.king.springposapi.user.UserDTO;

import java.util.List;
import java.util.UUID;

public record ProductOrderDTO(
        String orderNo,
        OrderStatus status,
        Boolean isPaid,
        PaymentEntryDTO paymentEntry,
        UserDTO user,
        List<OrderItemDTO> orderItems
) {
}
