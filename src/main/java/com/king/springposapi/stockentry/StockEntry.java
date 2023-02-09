package com.king.springposapi.stockentry;


import com.king.springposapi.storeproductstock.StoreProductStock;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class StockEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StockType type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StockEntryStatus status;

    @Column(nullable = false)
    private Integer quantity_supplied;

    @Column(nullable = false)
    private Float unit_price;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "store_product_stock_id")
    private StoreProductStock storeProductStock;

    public StockEntry(StockType type, StockEntryStatus status, Integer quantity_supplied, Float unit_price, StoreProductStock storeProductStock) {
        this.type = type;
        this.status = status;
        this.quantity_supplied = quantity_supplied;
        this.unit_price = unit_price;
        this.storeProductStock = storeProductStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StockEntry that = (StockEntry) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
