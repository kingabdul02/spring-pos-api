package com.king.springposapi.paymententry;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public record PaymentEntryDTOMapper() implements Function<PaymentEntry, PaymentEntryDTO> {
    @Override
    public PaymentEntryDTO apply(PaymentEntry paymentEntry) {
        return new PaymentEntryDTO(
                paymentEntry.getId(),
                paymentEntry.getPaymentRef(),
                paymentEntry.getAmount(),
                paymentEntry.getTotal(),
                paymentEntry.getTransactionFee(),
                paymentEntry.getVat(),
                paymentEntry.getPaymentMethod()
        );
    }
}
