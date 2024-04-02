package com.smirnoff.home.ui.components.finance.history;

import com.smirnoff.home.ui.components.MainLayout;
import com.smirnoff.home.ui.components.finance.history.dialog.EditOperationHistoryDialog;
import com.smirnoff.home.ui.model.finance.fund.FundFilterModel;
import com.smirnoff.home.ui.model.finance.history.OperationHistoryModel;
import com.smirnoff.home.ui.service.finance.history.OperationHistoryService;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.HasMenuItems;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.context.ApplicationContext;
import org.vaadin.klaudeta.PaginatedGrid;

import java.util.Set;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;

@PageTitle("Transaction history")
@Route(value = "finance/history", layout = MainLayout.class)
public class HistoryListView extends VerticalLayout implements CallbackDataProvider.FetchCallback<OperationHistoryModel, Void>,
        CallbackDataProvider.CountCallback<OperationHistoryModel, Void> {

    private final ApplicationContext applicationContext;
    private final OperationHistoryService operationHistoryService;
    private final PaginatedGrid<OperationHistoryModel, FundFilterModel> grid;

    public HistoryListView(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.operationHistoryService = applicationContext.getBean(OperationHistoryService.class);

        HorizontalLayout toolbar = new HorizontalLayout();
        Button addButton = new Button(new Icon());
        addButton.addThemeVariants(ButtonVariant.LUMO_ICON);
        addButton.setAriaLabel("Add item");
        toolbar.add(addButton);

        Component menuBar = prepareToolbar();
        add(menuBar);

        grid = new PaginatedGrid<>();
        grid.addColumn(OperationHistoryModel::getId).setHeader("Id");

        grid.setDataProvider(new CallbackDataProvider(this, this));

        grid.setPageSize(30);
        grid.setSizeFull();

        add(grid);
    }

    private Component prepareToolbar() {
        MenuBar menuBar = new MenuBar();

        createIconItem(menuBar, VaadinIcon.PLUS, "Add", event -> {
            createAddOperationDialog();
        });

        createIconItem(menuBar, VaadinIcon.EDIT, "Edit", event -> {
            Set<OperationHistoryModel> selectedItems = grid.getSelectedItems();
            if (selectedItems.size() == 1) {
                createEditDialog(selectedItems.stream().toList().getFirst());
            }
        });

        createIconItem(menuBar, VaadinIcon.TRASH, "Delete", event -> {
            for (OperationHistoryModel operation : grid.getSelectedItems()) {
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

    private void createEditDialog(OperationHistoryModel operationHistoryModel) {
        EditOperationHistoryDialog dialog = applicationContext.getBean(EditOperationHistoryDialog.class);
        dialog.saveClickListener(event -> {
            operationHistoryService.update(dialog.getOperation());
            dialog.close();
            grid.refreshPaginator();
        });

        dialog.open();
    }

    @Override
    public Stream<OperationHistoryModel> fetch(Query<OperationHistoryModel, Void> query) {
        return operationHistoryService.getList().stream();
    }

    @Override
    public int count(Query<OperationHistoryModel, Void> query) {
        return operationHistoryService.getList().size();
    }

    private void createIconItem(HasMenuItems menu, VaadinIcon iconName, String ariaLabel, ComponentEventListener<ClickEvent<MenuItem>> clickListener) {
        Icon icon = new Icon(iconName);

        MenuItem item = menu.addItem(icon, clickListener);
        if (nonNull(ariaLabel)) {
            item.setAriaLabel(ariaLabel);
        }

    }
}
