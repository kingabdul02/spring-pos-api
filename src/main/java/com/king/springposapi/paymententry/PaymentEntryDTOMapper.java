package com.king.springposapi.paymententry;

import java.util.function.Function;

public record PaymentEntryDTOMapper() implements Function<PaymentEntry, PaymentEntryDTO> {
    @Override
    public PaymentEntryDTO apply(PaymentEntry paymentEntry) {
        return null;
    }
}
