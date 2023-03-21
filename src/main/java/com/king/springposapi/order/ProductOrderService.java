package com.king.springposapi.order;

import com.king.springposapi.orderitem.OrderItem;
import com.king.springposapi.orderitem.OrderItemDTO;
import com.king.springposapi.orderitem.OrderItemResponse;
import com.king.springposapi.paymententry.PaymentEntry;
import com.king.springposapi.paymententry.PaymentEntryDTO;
import com.king.springposapi.paymententry.PaymentEntryDTOMapper;
import com.king.springposapi.product.Product;
import com.king.springposapi.product.ProductDTO;
import com.king.springposapi.product.ProductDTOMapper;
import com.king.springposapi.product.ProductRepository;
import com.king.springposapi.user.UserDTOMapper;
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
    private final PaymentEntryDTOMapper paymentEntryDTOMapper;
    private final UserDTOMapper userDTOMapper;
    private final ProductDTOMapper productDTOMapper;


    @Transactional
    public OrderResponse create(ProductOrderCreateRequest request){
        var ref = new Object() {
            float amount = 0;
        };
        float vat = 0;
        float transaction_fee = 0;
        // getLoggedIn user to get user_id
        var user = userService.getLoggedInUser().orElseThrow(() -> new IllegalArgumentException("User not found"));

        // generate paymentRef
        var paymentRef = Long.toHexString(Instant.now().toEpochMilli());

        List<OrderItem> orderItems = request.orderItems()
                .stream()
                .map(item -> {
                    Product product = productRepository.findById(item.product_id())
                            .orElseThrow(() -> new IllegalArgumentException("Product not found"));
                    ref.amount += calcTotal(product.getPrice(), item.quantity());
                    return new OrderItem(item.quantity(), product);
                }).collect(Collectors.toList());


        // create payment entry to get payment_entry_id
        PaymentEntry paymentEntry = new PaymentEntry(
                "PAY_"+paymentRef,
                ref.amount,
                ref.amount + vat + transaction_fee,
                request.paymentMethod()
        );
        var entry = paymentEntryRepository.save(paymentEntry);


        //  place order
        ProductOrder productOrder = new ProductOrder(
                "Order_"+paymentRef,
                OrderStatus.APPROVED,
                true,
                entry,
                user,
                orderItems
        );

        productOrder.getOrderItems().forEach(item -> item.setProductOrder(productOrder));

        var order = productOrderRepository.save(productOrder);

        OrderResponse response = new OrderResponse(
                productOrder.getOrderNo(),
                productOrder.getStatus(),
                productOrder.getIsPaid(),
                paymentEntryDTOMapper.apply(productOrder.getPaymentEntry()),
                userDTOMapper.apply(productOrder.getUser()),
                productOrder.getOrderItems().stream().map(orderItem -> new OrderItemResponse(orderItem.getQuantity(),
                        productDTOMapper.apply(orderItem.getProduct())
                        )
                ).toList());
        return response;
    }

    public float calcTotal(float amount, int quantity){
        float total = amount * quantity;
        return total;
    }
}
