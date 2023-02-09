package com.king.springposapi.order;

import com.king.springposapi.paymententry.PaymentEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentEntryRepository extends JpaRepository<PaymentEntry, UUID> {
}