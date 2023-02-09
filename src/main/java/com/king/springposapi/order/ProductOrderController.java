package com.king.springposapi.order;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class ProductOrderController {
    private final ProductOrderService productOrderService;

    @PostMapping
    public ProductOrder create(@RequestBody ProductOrderCreateRequest request){
        return productOrderService.create(request);
    }
}
