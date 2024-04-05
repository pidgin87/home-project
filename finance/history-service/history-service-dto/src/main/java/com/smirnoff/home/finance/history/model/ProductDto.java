package com.smirnoff.home.finance.history.model;

import lombok.Builder;

@Builder
public record ProductDto(
        String id,
        String name,
        ProductTypeEnum type
) {
}
