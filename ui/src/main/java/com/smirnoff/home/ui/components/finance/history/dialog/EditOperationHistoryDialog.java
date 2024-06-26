package com.smirnoff.home.ui.components.finance.history.dialog;

import com.smirnoff.home.finance.history.model.OperationHistoryDto;
import com.smirnoff.home.ui.components.common.CurrencyComboBox;
import com.smirnoff.home.ui.components.common.FundComboBox;
import com.smirnoff.home.ui.components.common.ProductComboBox;
import com.smirnoff.home.ui.model.finance.history.OperationHistoryModel;
import com.smirnoff.home.ui.service.finance.dictionary.DictionaryService;
import com.smirnoff.home.ui.service.finance.fund.FundService;
import com.smirnoff.home.ui.service.finance.product.ProductService;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.Autocomplete;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.util.Objects.nonNull;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
public class EditOperationHistoryDialog extends Dialog {

    private final OperationHistoryDto operationHistoryModel;
    private final DictionaryService dictionaryService;
    private final ProductService productService;
    private final FundService fundService;

    private BigDecimalField sourceAmountTextField;
    private ProductComboBox sourceProductComboBox;
    private FundComboBox sourceFundComboBox;
    private CurrencyComboBox sourceCurrencyComboBox;

    private BigDecimalField destinationAmountTextField;
    private ProductComboBox destinationProductComboBox;
    private FundComboBox destinationFundComboBox;
    private CurrencyComboBox destinationCurrencyComboBox;
    private DateTimePicker createdDateTimePicker;

    private TextArea descriptionTextField;
    private Button saveButton;

    @Autowired
    public EditOperationHistoryDialog(DictionaryService dictionaryService,
                                      ProductService productService,
                                      FundService fundService) {
        this("Create operation", dictionaryService, productService, fundService, null);
    }

    private EditOperationHistoryDialog(String title,
                                       DictionaryService dictionaryService,
                                       ProductService productService,
                                       FundService fundService,
                                       OperationHistoryDto operationHistoryModel) {
        this.operationHistoryModel = operationHistoryModel;
        this.dictionaryService = dictionaryService;
        this.productService = productService;
        this.fundService = fundService;

        setDraggable(false);
        setResizable(false);
        setHeaderTitle(title);

        VerticalLayout mainForm = new VerticalLayout();
        mainForm.setSpacing(true);
        add(mainForm);

        HorizontalLayout additionalInfoLayout = new HorizontalLayout();

        createdDateTimePicker = new DateTimePicker();
        createdDateTimePicker.setValue(LocalDateTime.now());
        additionalInfoLayout.add(createdDateTimePicker);

        mainForm.add(additionalInfoLayout);

        HorizontalLayout verticalLayout = new HorizontalLayout();
        verticalLayout.setPadding(false);
        verticalLayout.setMargin(false);

        VerticalLayout sourceComponent = prepareSourceComponent();
        sourceComponent.setPadding(false);
        sourceComponent.setSpacing(false);
        sourceComponent.setWidth(48, Unit.PERCENTAGE);
        verticalLayout.add(sourceComponent);

        VerticalLayout divider = new VerticalLayout();
        divider.setWidth(4, Unit.PERCENTAGE);
        verticalLayout.add(divider);

        VerticalLayout destinationComponent = prepareDestinationComponent();
        destinationComponent.setPadding(false);
        destinationComponent.setSpacing(false);
        destinationComponent.setWidth(48, Unit.PERCENTAGE);
        verticalLayout.add(destinationComponent);

        mainForm.add(verticalLayout);

        descriptionTextField = new TextArea();
        descriptionTextField.setMaxLength(255);
        descriptionTextField.setWidthFull();
        descriptionTextField.setPlaceholder("Description");
        descriptionTextField.setClearButtonVisible(true);
        mainForm.add(descriptionTextField);

        if (nonNull(this.operationHistoryModel)) {
            initValue(this.operationHistoryModel);
        }

        this.saveButton = new Button();
        this.saveButton.setText("Save");

        getFooter().add(this.saveButton);
    }

    private VerticalLayout prepareSourceComponent() {
        VerticalLayout layout = new VerticalLayout();

        HorizontalLayout productAndFundLayout = new HorizontalLayout();

        sourceProductComboBox = new ProductComboBox(productService);
        sourceProductComboBox.setPlaceholder("Source product");
        sourceProductComboBox.setWidth(40, Unit.PERCENTAGE);
        productAndFundLayout.add(sourceProductComboBox);

        sourceFundComboBox = new FundComboBox(fundService);
        sourceFundComboBox.setPlaceholder("Source fund");
        sourceFundComboBox.setWidth(60, Unit.PERCENTAGE);
        productAndFundLayout.add(sourceFundComboBox);

        layout.add(productAndFundLayout);

        HorizontalLayout moneyLayout = new HorizontalLayout();
        sourceAmountTextField = new BigDecimalField();
        sourceAmountTextField.addThemeVariants(
                TextFieldVariant.LUMO_ALIGN_RIGHT
        );
        sourceAmountTextField.setAutocomplete(Autocomplete.OFF);
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

        HorizontalLayout productAndFundLayout = new HorizontalLayout();

        destinationProductComboBox = new ProductComboBox(productService);
        destinationProductComboBox.setPlaceholder("Destination product");
        destinationProductComboBox.setWidth(40, Unit.PERCENTAGE);
        productAndFundLayout.add(destinationProductComboBox);

        destinationFundComboBox = new FundComboBox(fundService);
        destinationFundComboBox.setPlaceholder("Destination fund");
        destinationFundComboBox.setWidth(60, Unit.PERCENTAGE);
        productAndFundLayout.add(destinationFundComboBox);

        layout.add(productAndFundLayout);

        HorizontalLayout moneyLayout = new HorizontalLayout();
        destinationAmountTextField = new BigDecimalField();
        destinationAmountTextField.addThemeVariants(
                TextFieldVariant.LUMO_ALIGN_RIGHT
        );
        destinationAmountTextField.setAutocomplete(Autocomplete.OFF);
        destinationAmountTextField.setWidth(70, Unit.PERCENTAGE);
        destinationAmountTextField.setPlaceholder("Destination amount");
        moneyLayout.add(destinationAmountTextField);

        destinationCurrencyComboBox = new CurrencyComboBox(dictionaryService);
        destinationCurrencyComboBox.setWidth(30, Unit.PERCENTAGE);
        moneyLayout.add(destinationCurrencyComboBox);

        layout.add(moneyLayout);

        return layout;
    }

    private void initValue(OperationHistoryDto operation) {
        descriptionTextField.setValue(operation.getDescription());
        createdDateTimePicker.setValue(operation.getCreatedDate().toLocalDateTime());
    }

    public void saveClickListener(ComponentEventListener<ClickEvent<Button>> saveClickListener) {
        saveButton.addClickListener(saveClickListener);
    }

    public OperationHistoryModel getOperation() {
        return OperationHistoryModel.builder()

                .sourceProduct(sourceProductComboBox.getValue())
                .sourceFund(sourceFundComboBox.getValue())
                .sourceCurrency(sourceCurrencyComboBox.getValue())
                .sourceAmount(sourceAmountTextField.getValue())

                .destinationProduct(destinationProductComboBox.getValue())
                .destinationFund(destinationFundComboBox.getValue())
                .destinationCurrency(destinationCurrencyComboBox.getValue())
                .destinationAmount(destinationAmountTextField.getValue())

                .description(descriptionTextField.getValue())
                .createdDate(createdDateTimePicker.getValue().atZone(ZoneId.systemDefault()).toOffsetDateTime())

                .build();
    }
}
