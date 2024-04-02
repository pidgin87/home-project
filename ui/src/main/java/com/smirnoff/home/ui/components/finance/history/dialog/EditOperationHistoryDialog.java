package com.smirnoff.home.ui.components.finance.history.dialog;

import com.smirnoff.home.platform.dictionary.dto.currency.CurrencyModel;
import com.smirnoff.home.ui.components.common.CurrencyComboBox;
import com.smirnoff.home.ui.model.finance.history.OperationHistoryModel;
import com.smirnoff.home.ui.model.finance.product.ProductModel;
import com.smirnoff.home.ui.service.finance.dictionary.DictionaryService;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import static java.util.Objects.nonNull;

public class EditOperationHistoryDialog extends Dialog {

    private final OperationHistoryModel operationHistoryModel;
    private final DictionaryService dictionaryService;
    private TextField sourceAmountTextField;
    private ComboBox<ProductModel> sourceProductComboBox;
    private ComboBox<CurrencyModel> sourceCurrencyComboBox;

    private TextField destinationAmountTextField;
    private ComboBox<ProductModel> destinationProductComboBox;
    private ComboBox<CurrencyModel> destinationCurrencyComboBox;

    private TextArea descriptionTextField;
    private Button saveButton;

    public EditOperationHistoryDialog(DictionaryService dictionaryService) {
        this("Create operation", dictionaryService, null);
    }

    public EditOperationHistoryDialog(OperationHistoryModel operation,
                                      DictionaryService dictionaryService) {
        this("Create operation", dictionaryService, operation);
    }

    public EditOperationHistoryDialog(String title,
                                      DictionaryService dictionaryService,
                                      OperationHistoryModel operationHistoryModel) {
        this.operationHistoryModel = operationHistoryModel;
        this.dictionaryService = dictionaryService;
        setDraggable(true);
        setResizable(false);
        setCloseOnEsc(true);
        setHeaderTitle(title);

        HorizontalLayout verticalLayout = new HorizontalLayout();
        VerticalLayout sourceComponent = prepareSourceComponent();
        sourceComponent.setWidth(48, Unit.PERCENTAGE);
        verticalLayout.add(sourceComponent);

        VerticalLayout divider = new VerticalLayout();
        divider.setWidth(4, Unit.PERCENTAGE);
        verticalLayout.add(divider);

        VerticalLayout destinationComponent = prepareDestinationComponent();
        sourceComponent.setWidth(48, Unit.PERCENTAGE);
        verticalLayout.add(destinationComponent);

        add(verticalLayout);

        descriptionTextField = new TextArea();
        descriptionTextField.setMaxLength(255);
        descriptionTextField.setWidthFull();
        descriptionTextField.setLabel("Description");
        descriptionTextField.setClearButtonVisible(true);
        add(descriptionTextField);

        if (nonNull(this.operationHistoryModel)) {
            initValue(this.operationHistoryModel);
        }

        this.saveButton = new Button();
        this.saveButton.setText("Save");

        getFooter().add(this.saveButton);
    }

    private VerticalLayout prepareSourceComponent() {
        VerticalLayout layout = new VerticalLayout();

        sourceProductComboBox = new ComboBox<>();
        sourceProductComboBox.setPlaceholder("Source product");
        sourceProductComboBox.setWidthFull();
        layout.add(sourceProductComboBox);

        HorizontalLayout moneyLayout = new HorizontalLayout();
        sourceAmountTextField = new TextField();
        sourceAmountTextField.setWidth(70, Unit.PERCENTAGE);
        sourceAmountTextField.setPlaceholder("Source amount");
        moneyLayout.add(sourceAmountTextField);

        sourceCurrencyComboBox = new CurrencyComboBox(dictionaryService);
        sourceCurrencyComboBox.setWidth(30, Unit.PERCENTAGE);
        moneyLayout.add(sourceCurrencyComboBox);

        layout.add(moneyLayout);

        return layout;
    }

    private VerticalLayout prepareDestinationComponent() {
        VerticalLayout layout = new VerticalLayout();

        destinationProductComboBox = new ComboBox<>();
        destinationProductComboBox.setPlaceholder("Destination product");
        destinationProductComboBox.setWidthFull();
        layout.add(destinationProductComboBox);

        HorizontalLayout moneyLayout = new HorizontalLayout();
        destinationAmountTextField = new TextField();
        destinationAmountTextField.setWidth(70, Unit.PERCENTAGE);
        destinationAmountTextField.setPlaceholder("Destination amount");
        moneyLayout.add(destinationAmountTextField);

        destinationCurrencyComboBox = new CurrencyComboBox(dictionaryService);
        destinationCurrencyComboBox.setWidth(30, Unit.PERCENTAGE);
        moneyLayout.add(destinationCurrencyComboBox);

        layout.add(moneyLayout);

        return layout;
    }

    private void initValue(OperationHistoryModel operation) {
        descriptionTextField.setValue(operation.getDescription());
    }

    public void saveClickListener(ComponentEventListener<ClickEvent<Button>> saveClickListener) {
        saveButton.addClickListener(saveClickListener);
    }

    public String getOperationDescription() {
        return descriptionTextField.getValue();
    }

    public String getOperationId() {
        return operationHistoryModel.getId();
    }

    public OperationHistoryModel getOperation() {
        return new OperationHistoryModel(
                getOperationId(),
                getOperationDescription()
        );
    }
}
