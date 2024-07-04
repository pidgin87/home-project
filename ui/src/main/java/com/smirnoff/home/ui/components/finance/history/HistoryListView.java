package com.smirnoff.home.ui.components.finance.history;

import com.smirnoff.home.finance.fund.model.Fund;
import com.smirnoff.home.finance.history.model.OperationHistoryDto;
import com.smirnoff.home.finance.product.model.ProductModel;
import com.smirnoff.home.ui.components.MainView;
import com.smirnoff.home.ui.components.finance.history.dialog.EditOperationHistoryDialog;
import com.smirnoff.home.ui.model.finance.fund.FundFilterModel;
import com.smirnoff.home.ui.service.finance.history.MoneyTranslator;
import com.smirnoff.home.ui.service.finance.history.OperationHistoryService;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.HasMenuItems;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.context.ApplicationContext;
import org.vaadin.klaudeta.PaginatedGrid;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;

@PermitAll
@PageTitle("Transaction history")
@Route(value = "finance/history", layout = MainView.class)
public class HistoryListView extends VerticalLayout implements CallbackDataProvider.FetchCallback<OperationHistoryDto, Void>,
        CallbackDataProvider.CountCallback<OperationHistoryDto, Void> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    private final ApplicationContext applicationContext;
    private final OperationHistoryService operationHistoryService;
    private final MoneyTranslator moneyTranslator;
    private final PaginatedGrid<OperationHistoryDto, FundFilterModel> grid;

    public HistoryListView(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.operationHistoryService = applicationContext.getBean(OperationHistoryService.class);
        this.moneyTranslator = applicationContext.getBean(MoneyTranslator.class);

        HorizontalLayout toolbar = new HorizontalLayout();
        Button addButton = new Button(new Icon());
        addButton.addThemeVariants(ButtonVariant.LUMO_ICON);
        addButton.setAriaLabel("Add item");
        toolbar.add(addButton);

        Component menuBar = prepareToolbar();
        add(menuBar);

        grid = new PaginatedGrid<>();
        grid.addColumn(formatCreatedDate()).setWidth("20%").setHeader("Date");
        grid.addComponentColumn(this::getAmountColumn).setWidth("30%").setHeader("Amount");
        grid.addComponentColumn(this::getDescriptionColumn).setWidth("50%").setHeader("Description");

        grid.setDataProvider(new CallbackDataProvider(this, this));

        grid.setPageSize(30);
        grid.setSizeFull();

        add(grid);
    }

    private static ValueProvider<OperationHistoryDto, Object> formatCreatedDate() {
        return operationHistoryDto -> {
            OffsetDateTime createdDate = operationHistoryDto.getCreatedDate();
            return FORMATTER.format(createdDate);
        };
    }

    @Override
    public Stream<OperationHistoryDto> fetch(Query<OperationHistoryDto, Void> query) {
        return operationHistoryService.getList().stream();
    }

    @Override
    public int count(Query<OperationHistoryDto, Void> query) {
        return operationHistoryService.getList().size();
    }


    private Component getAmountColumn(OperationHistoryDto operation) {
        String sourceAmount = moneyTranslator.toString(operation.getSourceAmount(), operation.getSourceCurrency());
        String destinationAmount = moneyTranslator.toString(operation.getDestinationAmount(), operation.getDestinationCurrency());

        Span span = new Span();
        if (sourceAmount != null && destinationAmount != null) {
            span.add(sourceAmount);
            span.add(" -> ");
            span.setClassName("money-transfer");
            span.add(destinationAmount);
        } else if (sourceAmount != null) {
            span.add(sourceAmount);
            span.setClassName("money-outcome");
        } else if (destinationAmount != null) {
            span.add(destinationAmount);
            span.setClassName("money-income");
        }
        return span;
    }

    private Component getDescriptionColumn(OperationHistoryDto operation) {
        ProductModel sourceProduct = operation.getSourceProduct();
        Fund sourceFund = operation.getSourceFund();
        ProductModel destinationProduct = operation.getDestinationProduct();
        Fund destinationFund = operation.getDestinationFund();

        VerticalLayout descriptionLayout = new VerticalLayout();
        if (sourceProduct != null && sourceProduct.id() != null && destinationProduct != null && destinationProduct.id() != null) {
            createDescription(descriptionLayout,
                    sourceProduct.name() + " -> " + destinationProduct.name(),
                    sourceFund.name() + " -> " + destinationFund.name());
        } else if (sourceProduct != null && sourceFund.id() != null) {
            createDescription(descriptionLayout, sourceProduct.name(), sourceFund.name());
        } else if (destinationProduct != null && destinationProduct.id() != null) {
            createDescription(descriptionLayout,
                    destinationProduct.name(), destinationFund.name());
        }
        return descriptionLayout;
    }

    private static void createDescription(VerticalLayout descriptionLayout, String productText, String fundText) {
        HorizontalLayout productLayout = new HorizontalLayout();
        productLayout.add(new Span("Product: "));
        productLayout.add(createProductSpan(productText));
        descriptionLayout.add(productLayout);

        HorizontalLayout fundLayout = new HorizontalLayout();
        fundLayout.add(new Span("Fund: "));
        fundLayout.add(createFundSpan(fundText));
        descriptionLayout.add(fundLayout);
    }

    private static Span createProductSpan(String text) {
        Span productSpan = new Span(text);
        productSpan.setClassName("product-description");
        return productSpan;
    }

    private static Span createFundSpan(String text) {
        Span productSpan = new Span(text);
        productSpan.setClassName("fund-description");
        return productSpan;
    }

    private Component prepareToolbar() {
        MenuBar menuBar = new MenuBar();

        createIconItem(menuBar, VaadinIcon.PLUS, "Add", event -> {
            createAddOperationDialog();
        });

        createIconItem(menuBar, VaadinIcon.EDIT, "Edit", event -> {
            Set<OperationHistoryDto> selectedItems = grid.getSelectedItems();
            if (selectedItems.size() == 1) {
                createEditDialog(selectedItems.stream().toList().getFirst());
            }
        });

        createIconItem(menuBar, VaadinIcon.TRASH, "Delete", event -> {
            for (OperationHistoryDto operation : grid.getSelectedItems()) {
                operationHistoryService.delete(operation);
            }
            grid.refreshPaginator();
        });

        return menuBar;
    }

    private void createAddOperationDialog() {
        EditOperationHistoryDialog dialog = applicationContext.getBean(EditOperationHistoryDialog.class);
        dialog.saveClickListener(event -> {
            operationHistoryService.create(dialog.getOperation());
            dialog.close();
            grid.refreshPaginator();
        });

        dialog.open();
    }

    private void createEditDialog(OperationHistoryDto operationHistoryModel) {
        EditOperationHistoryDialog dialog = applicationContext.getBean(EditOperationHistoryDialog.class);
        dialog.saveClickListener(event -> {
            operationHistoryService.update(dialog.getOperation());
            dialog.close();
            grid.refreshPaginator();
        });

        dialog.open();
    }

    private void createIconItem(HasMenuItems menu, VaadinIcon iconName, String ariaLabel, ComponentEventListener<ClickEvent<MenuItem>> clickListener) {
        Icon icon = new Icon(iconName);

        MenuItem item = menu.addItem(icon, clickListener);
        if (nonNull(ariaLabel)) {
            item.setAriaLabel(ariaLabel);
        }

    }
}
