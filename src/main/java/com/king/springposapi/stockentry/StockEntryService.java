package com.king.springposapi.stockentry;

import com.king.springposapi.storeproductstock.StoreProductStockRepository;
import com.king.springposapi.validators.ObjectValidator;
import com.sun.nio.sctp.IllegalReceiveException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StockEntryService {
    private final StockEntryDTOMapper stockEntryDTOMapper;
    private final StockEntryRepository stockEntryRepository;
    private final StoreProductStockRepository storeProductStockRepository;

    private final ObjectValidator objectValidator;

    public List<StockEntryDTO> list(){
        return stockEntryRepository.findAll()
                .stream()
                .map(stockEntryDTOMapper)
                .toList();
    }

    public Optional<StockEntryDTO> getStockEntry(UUID id){
        return stockEntryRepository.findById(id)
                .stream().map(stockEntryDTOMapper)
                .findFirst();
    }

    public StockEntry create(@NotNull StockEntryCreateRequest request){
        var storeProductStock = storeProductStockRepository.findById(request.store_product_stock_id()).orElseThrow(() -> new IllegalArgumentException("StoreProductStock Not Found"));
        StockEntry stockEntry = new StockEntry(
                request.type(),
                request.status(),
                request.quantity_supplied(),
                request.unit_price(),
                storeProductStock
        );
        System.out.println(stockEntry);
        return stockEntryRepository.save(stockEntry);
    }

    public ResponseEntity<StockEntryDTO> update(StockEntryUpdateRequest request, UUID id){
        var entry = stockEntryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Entry Not Found"));

        entry.setType(request.type());
        entry.setStatus(request.status());
        entry.setQuantity_supplied(request.quantity_supplied());
        entry.setUnit_price(request.unit_price());

        objectValidator.validate(entry);
        stockEntryRepository.save(entry);

        return ResponseEntity.ok(stockEntryDTOMapper.apply(entry));
    }

    public void delete(UUID id){
        var entry = stockEntryRepository.findById(id).orElseThrow(() -> new IllegalReceiveException("Entry Not Found"));
        stockEntryRepository.deleteById(id);
    }
}
