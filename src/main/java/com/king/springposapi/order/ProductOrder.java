package com.king.springposapi.order;


import com.king.springposapi.orderitem.OrderItem;
import com.king.springposapi.paymententry.PaymentEntry;
import com.king.springposapi.user.User;
import com.king.springposapi.user.UserDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
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
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String orderNo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Boolean isPaid;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


    @OneToMany(mappedBy = "productOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<OrderItem> orderItems;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "payment_entry_id")
    private PaymentEntry paymentEntry;

    public ProductOrder(String orderNo, OrderStatus status, boolean isPaid, PaymentEntry paymentEntry, User user, List<OrderItem> orderItems) {
        this.orderNo = orderNo;
        this.isPaid = isPaid;
        this.status = status;
        this.paymentEntry = paymentEntry;
        this.user = user;
        this.orderItems = orderItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductOrder productOrder = (ProductOrder) o;
        return id != null && Objects.equals(id, productOrder.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
