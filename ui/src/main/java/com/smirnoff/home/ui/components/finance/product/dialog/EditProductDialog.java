package com.smirnoff.home.ui.components.finance.product.dialog;

import com.smirnoff.home.ui.model.finance.product.ProductModel;
import com.smirnoff.home.ui.model.finance.product.ProductTypeModel;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;

public class EditProductDialog extends Dialog {

    private final TextField productNameTextField;
    private final Button saveButton;
    private final Select<ProductTypeModel> productTypeSelect;

    public EditProductDialog() {
        this("Create new product");
    }

    public EditProductDialog(String title) {
        setDraggable(true);
        setResizable(false);
        setCloseOnEsc(true);
        setHeaderTitle(title);

        VerticalLayout layout = new VerticalLayout();
        add(layout);

        productNameTextField = new TextField();
        productNameTextField.setLabel("Product name");
        productNameTextField.setClearButtonVisible(true);
        layout.add(productNameTextField);

        productTypeSelect = new Select<>();
        productTypeSelect.setLabel("Product type");
        productTypeSelect.setItems(ProductTypeModel.values());
        productTypeSelect.setValue(ProductTypeModel.CARD);
        layout.add(productTypeSelect);

        this.saveButton = new Button();
        this.saveButton.setText("Save");

        getFooter().add(this.saveButton);
    }

    public EditProductDialog(ProductModel fund) {
        this("Update product");
        if (fund != null) {
            productNameTextField.setValue(fund.name());
        }
    }

    public void saveClickListener(ComponentEventListener<ClickEvent<Button>> saveClickListener) {
        saveButton.addClickListener(saveClickListener);
    }

    public String getProductName() {
        return productNameTextField.getValue();
    }

    public ProductTypeModel getProductType() {
        return productTypeSelect.getValue();
    }
}
