package com.smirnoff.home.ui.components.finance.fund;

import com.smirnoff.home.ui.components.MainLayout;
import com.smirnoff.home.ui.components.finance.fund.dialog.EditFundDialog;
import com.smirnoff.home.ui.model.finance.fund.FundFilterModel;
import com.smirnoff.home.ui.model.finance.fund.FundModel;
import com.smirnoff.home.ui.service.finance.fund.FundService;
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
import org.vaadin.klaudeta.PaginatedGrid;

import java.util.Set;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;

@PageTitle("List of funds")
@Route(value = "finance/fund", layout = MainLayout.class)
public class FundListView extends VerticalLayout implements CallbackDataProvider.FetchCallback<FundModel, Void>,
        CallbackDataProvider.CountCallback<FundModel, Void> {

    private final FundService fundService;
    private final PaginatedGrid<FundModel, FundFilterModel> grid;

    public FundListView(FundService fundService) {
        this.fundService = fundService;

        HorizontalLayout toolbar = new HorizontalLayout();
        Button addButton = new Button(new Icon());
        addButton.addThemeVariants(ButtonVariant.LUMO_ICON);
        addButton.setAriaLabel("Add item");
        toolbar.add(addButton);

        Component menuBar = prepareToolbar();
        add(menuBar);

        grid = new PaginatedGrid<>();
        grid.addColumn(FundModel::name).setHeader("Name");

        grid.setDataProvider(new CallbackDataProvider(this, this));

        grid.setPageSize(30);
        grid.setSizeFull();

        add(grid);
    }

    private Component prepareToolbar() {
        MenuBar menuBar = new MenuBar();

        createIconItem(menuBar, VaadinIcon.PLUS, "Add", event -> {
            createAddFundDialog();
        });

        createIconItem(menuBar, VaadinIcon.EDIT, "Edit", event -> {
            Set<FundModel> selectedItems = grid.getSelectedItems();
            if (selectedItems.size() == 1) {
                createEditFundDialog(selectedItems.stream().toList().getFirst());
            }
        });

        createIconItem(menuBar, VaadinIcon.TRASH, "Delete", event -> {
            for (FundModel fund : grid.getSelectedItems()) {
                fundService.delete(fund);
            }
            grid.refreshPaginator();
        });

        return menuBar;
    }

    private void createAddFundDialog() {
        EditFundDialog dialog = new EditFundDialog();
        dialog.saveClickListener(event -> {
            String fundName = dialog.getFundName();
            fundService.create(fundName);
            dialog.close();
            grid.refreshPaginator();
        });

        dialog.open();
    }

    private void createEditFundDialog(FundModel fundModel) {
        EditFundDialog dialog = new EditFundDialog(fundModel);
        dialog.saveClickListener(event -> {
            String fundName = dialog.getFundName();

            fundService.update(new FundModel(fundModel.id(), fundName));
            dialog.close();
            grid.refreshPaginator();
        });

        dialog.open();
    }

    @Override
    public Stream<FundModel> fetch(Query<FundModel, Void> query) {
        return fundService.getList().stream();
    }

    @Override
    public int count(Query<FundModel, Void> query) {
        return fundService.getList().size();
    }

    private void createIconItem(HasMenuItems menu, VaadinIcon iconName, String ariaLabel, ComponentEventListener<ClickEvent<MenuItem>> clickListener) {
        Icon icon = new Icon(iconName);

        MenuItem item = menu.addItem(icon, clickListener);
        if (nonNull(ariaLabel)) {
            item.setAriaLabel(ariaLabel);
        }

    }
}
