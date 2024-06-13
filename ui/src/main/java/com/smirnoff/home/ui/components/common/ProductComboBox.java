package com.smirnoff.home.ui.components.common;

import com.smirnoff.home.finance.product.model.ProductModel;
import com.smirnoff.home.ui.service.finance.product.ProductService;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.function.SerializableFunction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
public class ProductComboBox extends ComboBox<ProductModel> implements ComboBox.FetchItemsCallback<ProductModel>,
        SerializableFunction<String, Integer> {

    private final ProductService productService;

    public ProductComboBox(ProductService productService) {
        this.productService = productService;
        setDataProvider(this, this);
        setItemLabelGenerator(ProductModel::name);
        getStyle().set("--vaadin-combo-box-overlay-width", "16em");
    }

    @Override
    public Stream<ProductModel> fetchItems(String s, int i, int i1) {
        return productService.getList().stream();
    }

    @Override
    public Integer apply(String s) {
        return productService.getList().size();
    }
}
