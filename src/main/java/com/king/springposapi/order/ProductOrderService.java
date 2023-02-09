package com.king.springposapi.order;

import com.king.springposapi.orderitem.OrderItem;
import com.king.springposapi.paymententry.PaymentEntry;
import com.king.springposapi.product.Product;
import com.king.springposapi.product.ProductRepository;
import com.king.springposapi.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductOrderService {
    private final ProductRepository productRepository;
    private final PaymentEntryRepository paymentEntryRepository;
    private final UserService userService;
    private final ProductOrderRepository productOrderRepository;

    @Transactional
    public ProductOrder create(ProductOrderCreateRequest request){
        // TODO: 02/02/2023  getLoggedIn user to get user_id
        var user = userService.getLoggedInUser().orElseThrow(() -> new IllegalArgumentException("User not found"));

        // TODO: 02/02/2023 generate paymentRef
        var paymentRef = Long.toHexString(Instant.now().toEpochMilli());

        // TODO: 02/02/2023 create payment entry to get payment_entry_id
        PaymentEntry paymentEntry = new PaymentEntry(
                "PAY_"+paymentRef,
                request.amount(),
                request.total(),
                request.paymentMethod()
        );
        var entry = paymentEntryRepository.save(paymentEntry);

        List<OrderItem> orderItems = request.orderItems()
                .stream()
                .map(item -> {
                    Product product = productRepository.findById(item.product_id())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
                 return new OrderItem(item.quantity(), product);
        }).collect(Collectors.toList());

        // TODO: 02/02/2023  place order
        ProductOrder productOrder = new ProductOrder(
                "Order_"+paymentRef,
                OrderStatus.APPROVED,
                true,
                entry,
                user,
                orderItems
        );

        productOrder.getOrderItems().forEach(item -> item.setProductOrder(productOrder));

        return productOrderRepository.save(productOrder);
    }
}