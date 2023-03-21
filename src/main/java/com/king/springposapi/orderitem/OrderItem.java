package com.king.springposapi.orderitem;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.king.springposapi.order.ProductOrder;
import com.king.springposapi.product.Product;
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
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private Integer quantity;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_order_id")
    private ProductOrder productOrder;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public OrderItem(Integer quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderItem orderItem = (OrderItem) o;
        return id != null && Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
