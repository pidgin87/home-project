package com.smirnoff.home.platform.dictionary.dto.stock;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockModel {
    private String id;
    private String ticker;
    private String name;
}
