package com.smirnoff.home.finance.history.persistance.repository.projections;

import java.math.BigDecimal;

public record ProductBalanceProjection(String productId,
                                       BigDecimal amount) {
}
