package com.king.springposapi.order;

import com.king.springposapi.orderitem.OrderItemDTO;
import com.king.springposapi.orderitem.OrderItemResponse;
import com.king.springposapi.paymententry.PaymentEntryDTO;
import com.king.springposapi.user.UserDTO;

import java.util.List;

public record OrderResponse(
        String orderNo,
        OrderStatus status,
        Boolean isPaid,
        PaymentEntryDTO paymentEntry,
        UserDTO user,
        List<OrderItemResponse> orderItems
) {
}
