package com.king.springposapi.store;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.king.springposapi.storeproductstock.StoreProductStock;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private  String name;

    @Column(nullable = false)
    private  String location;

    @Enumerated(EnumType.STRING)
    private StoreStatus status = StoreStatus.ACTIVE;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private List<StoreProductStock> storeProductStocks;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Store(String name, String location) {
        this.name = name;
        this.location = location;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Store store = (Store) o;
        return id != null && Objects.equals(id, store.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
