package com.smirnoff.home.ui.components.common;

import com.smirnoff.home.finance.fund.model.Fund;
import com.smirnoff.home.ui.service.finance.fund.FundService;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.function.SerializableFunction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
public class FundComboBox extends ComboBox<Fund> implements ComboBox.FetchItemsCallback<Fund>,
        SerializableFunction<String, Integer> {

    private final FundService fundService;

    public FundComboBox(FundService fundService) {
        this.fundService = fundService;
        setDataProvider(this, this);
        setItemLabelGenerator(Fund::name);
        getStyle().set("--vaadin-combo-box-overlay-width", "16em");
    }

    @Override
    public Stream<Fund> fetchItems(String s, int i, int i1) {
        return fundService.getList().stream();
    }

    @Override
    public Integer apply(String s) {
        return fundService.getList().size();
    }
}
