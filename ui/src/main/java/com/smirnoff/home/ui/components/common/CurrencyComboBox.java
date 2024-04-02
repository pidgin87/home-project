package com.smirnoff.home.ui.components.common;

import com.smirnoff.home.platform.dictionary.dto.currency.CurrencyModel;
import com.smirnoff.home.ui.service.finance.dictionary.DictionaryService;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.function.SerializableFunction;

import java.util.stream.Stream;

public class CurrencyComboBox extends ComboBox<CurrencyModel>
        implements ComboBox.FetchItemsCallback<CurrencyModel>, SerializableFunction<String, Integer> {

    private final DictionaryService dictionaryService;

    public CurrencyComboBox(DictionaryService dictionaryService) {
        setDataProvider(this, this);
        this.dictionaryService = dictionaryService;
    }

    @Override
    public Stream<CurrencyModel> fetchItems(String s, int i, int i1) {
        return dictionaryService.getCurrencies().stream();
    }

    @Override
    public Integer apply(String s) {
        return 100;
    }
}
