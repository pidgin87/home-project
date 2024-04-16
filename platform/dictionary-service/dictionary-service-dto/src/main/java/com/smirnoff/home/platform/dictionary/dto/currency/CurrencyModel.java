package com.smirnoff.home.platform.dictionary.dto.currency;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrencyModel {
    private String id;
    private String iso;
    private String name;
    private String symbol;
}
