package com.king.springposapi.stockentry;

import com.king.springposapi.storeproductstock.StoreProductStockDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class StockEntryDTOMapper implements Function<StockEntry, StockEntryDTO> {
    private final StoreProductStockDTOMapper storeProductStockDTOMapper;

    @Override
    public StockEntryDTO apply(StockEntry stockEntry) {
        return new StockEntryDTO(
                stockEntry.getId(),
                stockEntry.getType(),
                stockEntry.getStatus(),
                stockEntry.getQuantity_supplied(),
                stockEntry.getUnit_price(),
                storeProductStockDTOMapper.apply(stockEntry.getStoreProductStock())
        );
    }
}
