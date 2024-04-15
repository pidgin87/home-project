package com.smirnoff.home.ui.components.common;

import com.smirnoff.home.ui.model.finance.fund.FundModel;
import com.smirnoff.home.ui.model.finance.product.ProductModel;
import com.smirnoff.home.ui.service.finance.fund.FundService;
import com.smirnoff.home.ui.service.finance.product.ProductService;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.function.SerializableFunction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
public class FundComboBox extends ComboBox<FundModel> implements ComboBox.FetchItemsCallback<FundModel>,
        SerializableFunction<String, Integer> {

    private final FundService fundService;

    public FundComboBox(FundService fundService) {
        this.fundService = fundService;
        setDataProvider(this, this);
        setItemLabelGenerator(FundModel::name);
        getStyle().set("--vaadin-combo-box-overlay-width", "16em");
    }

    @Override
    public Stream<FundModel> fetchItems(String s, int i, int i1) {
        return fundService.getList().stream();
    }

    @Override
    public Integer apply(String s) {
        return fundService.getList().size();
    }
}
