package com.smirnoff.home.ui.components.finance.fund.dialog;

import com.smirnoff.home.ui.model.finance.fund.FundModel;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.textfield.TextField;

public class EditFundDialog extends Dialog {

    private final TextField fundNameTextField;
    private final Button saveButton;

    public EditFundDialog() {
        this("Create new fund");
    }

    public EditFundDialog(String title) {
        setDraggable(true);
        setResizable(false);
        setCloseOnEsc(true);
        setHeaderTitle(title);

        fundNameTextField = new TextField();
        fundNameTextField.setLabel("Fund name");
        fundNameTextField.setClearButtonVisible(true);
        add(fundNameTextField);

        this.saveButton = new Button();
        this.saveButton.setText("Save");

        getFooter().add(this.saveButton);
    }

    public EditFundDialog(FundModel fund) {
        this("Update fund");
        if (fund != null) {
            fundNameTextField.setValue(fund.name());
        }
    }

    public void saveClickListener(ComponentEventListener<ClickEvent<Button>> saveClickListener) {
        saveButton.addClickListener(saveClickListener);
    }

    public String getFundName() {
        return fundNameTextField.getValue();
    }
}
