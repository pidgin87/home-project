package com.smirnoff.home.ui.components.finance.fund;

import com.smirnoff.home.ui.adapter.finance.fund.FundAdapter;
import com.smirnoff.home.ui.components.MainLayout;
import com.smirnoff.home.ui.model.finance.fund.FundFilterModel;
import com.smirnoff.home.ui.model.finance.fund.FundModel;
import com.smirnoff.home.ui.service.finance.fund.FundService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.BackEndDataProvider;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.klaudeta.PaginatedGrid;

import java.util.List;
import java.util.stream.Stream;

@PageTitle("List of funds")
@Route(value = "finance/fund", layout = MainLayout.class)
public class FundListView extends VerticalLayout implements CallbackDataProvider.FetchCallback<FundModel, Void>,
        CallbackDataProvider.CountCallback<FundModel, Void> {

    private final FundService fundService;

    public FundListView(FundService fundService) {
        this.fundService = fundService;

        PaginatedGrid<FundModel, FundFilterModel> grid = new PaginatedGrid<>();
        grid.addColumn(FundModel::name).setHeader("Name");

        grid.setDataProvider(new CallbackDataProvider(this, this));

        grid.setPageSize(30);
        grid.setSizeFull();
        add(grid);
    }

    @Override
    public Stream<FundModel> fetch(Query<FundModel, Void> query) {
        return List.of(new FundModel("hello")).stream();
    }

    @Override
    public int count(Query<FundModel, Void> query) {
        return 1;
    }
}
