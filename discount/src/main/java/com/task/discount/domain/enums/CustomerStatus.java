package com.task.discount.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum CustomerStatus {
    REGULAR(0),
    GOLD(10),
    PLATINUM(20);

    private final int discountPercent;

    public BigDecimal applyDiscountPercent(BigDecimal amount) {
        BigDecimal discount = BigDecimal.valueOf(discountPercent).divide(BigDecimal.valueOf(100));
        return amount.multiply(BigDecimal.ONE.subtract(discount));
    }
}
