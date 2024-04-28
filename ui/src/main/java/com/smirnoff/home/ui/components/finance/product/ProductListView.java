package com.smirnoff.home.ui.components.finance.product;

import com.smirnoff.home.ui.components.MainView;
import com.smirnoff.home.ui.components.finance.product.dialog.EditProductDialog;
import com.smirnoff.home.ui.model.finance.fund.FundFilterModel;
import com.smirnoff.home.ui.model.finance.product.ProductModel;
import com.smirnoff.home.ui.model.finance.product.ProductTypeModel;
import com.smirnoff.home.ui.service.finance.product.ProductService;
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
import jakarta.annotation.security.PermitAll;
import org.vaadin.klaudeta.PaginatedGrid;

import java.util.Set;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;

@PermitAll
@PageTitle("List of products")
@Route(value = "finance/products", layout = MainView.class)
public class ProductListView extends VerticalLayout implements CallbackDataProvider.FetchCallback<ProductModel, Void>,
        CallbackDataProvider.CountCallback<ProductModel, Void> {

    private final PaginatedGrid<ProductModel, FundFilterModel> grid;
    private final ProductService productService;
    public ProductListView(ProductService productService) {
        this.productService = productService;

        HorizontalLayout toolbar = new HorizontalLayout();
        Button addButton = new Button(new Icon());
        addButton.addThemeVariants(ButtonVariant.LUMO_ICON);
        addButton.setAriaLabel("Add item");
        toolbar.add(addButton);

        Component menuBar = prepareToolbar();
        add(menuBar);

        grid = new PaginatedGrid<>();
        grid.addColumn(ProductModel::name).setHeader("Name");

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
            Set<ProductModel> selectedItems = grid.getSelectedItems();
            if (selectedItems.size() == 1) {
                createEditProductDialog(selectedItems.stream().toList().getFirst());
            }
        });

        createIconItem(menuBar, VaadinIcon.TRASH, "Delete", event -> {
            for (ProductModel product : grid.getSelectedItems()) {
                productService.delete(product);
            }
            grid.refreshPaginator();
        });

        return menuBar;
    }

    private void createAddFundDialog() {
        EditProductDialog dialog = new EditProductDialog();
        dialog.saveClickListener(event -> {
            String productName = dialog.getProductName();
            ProductTypeModel productType = dialog.getProductType();
            productService.create(productName, productType);
            dialog.close();
            grid.refreshPaginator();
        });

        dialog.open();
    }

    private void createEditProductDialog(ProductModel productModel) {
        EditProductDialog dialog = new EditProductDialog(productModel);
        dialog.saveClickListener(event -> {
            String productName = dialog.getProductName();

            productService.update(productModel.id(), productName);
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

    @Override
    public int count(Query<ProductModel, Void> query) {
        return 0;
    }

    @Override
    public Stream<ProductModel> fetch(Query<ProductModel, Void> query) {
        return productService.getList().stream();
    }
}
