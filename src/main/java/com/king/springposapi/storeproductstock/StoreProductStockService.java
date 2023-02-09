package com.king.springposapi.storeproductstock;

import com.king.springposapi.product.ProductRepository;
import com.king.springposapi.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreProductStockService {
    private final StoreProductStockRepository storeProductStockRepository;
    private final StoreProductStockDTOMapper storeProductStockDTOMapper;
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    public List<StoreProductStockDTO> list(){
        return storeProductStockRepository.findAll()
                .stream()
                .map(storeProductStockDTOMapper)
                .collect(Collectors.toList());
    }

    public Optional<StoreProductStockDTO> getProductStoreStock(UUID id){
        var storeProductStock = storeProductStockRepository.findById(id);
        return storeProductStock
                .stream()
                .map(storeProductStockDTOMapper)
                .findFirst();
    }

    public StoreProductStock create(@NotNull("This field is required") StoreProductStockCreateRequest request){
        var product = productRepository.findById(request.product_id()).orElseThrow(() -> new IllegalArgumentException("Product Not Found"));
        var store = storeRepository.findById(request.store_id()).orElseThrow(() -> new IllegalArgumentException("Store Not Found"));
        StoreProductStock storeProductStock = new StoreProductStock(
                request.unitPrice(),
                request.quantity(),
                request.restockTargetLevel(),
                request.holdingTargetLevel(),
                product,
                store
        );

        return storeProductStockRepository.save(storeProductStock);
    }

    public void update(StoreProductStockUpdateRequest request, UUID id){
        var productStoreStock = storeProductStockRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("StoreProductStock Not Found"));
//        update record
    }

    public void delete(UUID id){
        var storeProductStock = storeProductStockRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("StoreProductStock not found"));
        storeProductStockRepository.deleteById(id);
    }
}
