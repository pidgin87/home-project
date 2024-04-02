package com.smirnoff.home.ui.components.common;

import com.smirnoff.home.platform.dictionary.dto.currency.CurrencyModel;
import com.smirnoff.home.ui.service.finance.dictionary.DictionaryService;
import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.function.SerializableFunction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
public class CurrencyComboBox extends ComboBox<CurrencyModel> implements ComboBox.FetchItemsCallback<CurrencyModel>,
        SerializableFunction<String, Integer> {

    private final DictionaryService dictionaryService;

    public CurrencyComboBox(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
        setDataProvider(this, this);
        setItemLabelGenerator(CurrencyModel::getIso);
        getStyle().set("--vaadin-combo-box-overlay-width", "16em");
    }

    @Override
    public Stream<CurrencyModel> fetchItems(String s, int i, int i1) {
        return dictionaryService.getCurrencies().stream();
    }

    @Override
    public Integer apply(String s) {
        return dictionaryService.getCurrencies().size();
    }
}
