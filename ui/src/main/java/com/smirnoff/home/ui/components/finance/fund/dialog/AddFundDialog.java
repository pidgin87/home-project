package com.smirnoff.home.ui.components.finance.fund.dialog;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.textfield.TextField;

public class AddFundDialog extends Dialog {

    private final TextField fundNameTextField;
    private final Button saveButton;

    public AddFundDialog() {
        setDraggable(true);
        setResizable(false);
        setCloseOnEsc(true);
        setHeaderTitle("Create new fund");

        fundNameTextField = new TextField();
        fundNameTextField.setLabel("Fund name");
        fundNameTextField.setClearButtonVisible(true);
        add(fundNameTextField);

        this.saveButton = new Button();
        this.saveButton.setText("Save");

        getFooter().add(this.saveButton);
    }

    public void saveClickListener(ComponentEventListener<ClickEvent<Button>> saveClickListener) {
        saveButton.addClickListener(saveClickListener);
    }

    public String getFundName() {
        return fundNameTextField.getValue();
    }
}
