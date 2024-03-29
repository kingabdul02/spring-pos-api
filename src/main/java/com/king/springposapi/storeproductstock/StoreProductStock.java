package com.king.springposapi.storeproductstock;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.king.springposapi.product.Product;
import com.king.springposapi.stockentry.StockEntry;
import com.king.springposapi.store.Store;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class StoreProductStock {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Float unitPrice;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer restockTargetLevel;

    @Column(nullable = false)
    private Integer holdingTargetLevel;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "storeProductStock", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private List<StockEntry> stockEntries;

    public StoreProductStock(Float unitPrice, Integer quantity, Integer restockTargetLevel, Integer holdingTargetLevel, Product product, Store store) {
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.restockTargetLevel = restockTargetLevel;
        this.holdingTargetLevel= holdingTargetLevel;
        this.product = product;
        this.store = store;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StoreProductStock that = (StoreProductStock) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
