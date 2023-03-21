package com.king.springposapi.paymententry;

import java.util.UUID;

public record PaymentEntryDTO(
        UUID id,
        String paymentRef,
        Float amount,
        Float transactionFee,
        Float vat,
        Float total,
        PaymentMethod paymentMethod
) {
}
