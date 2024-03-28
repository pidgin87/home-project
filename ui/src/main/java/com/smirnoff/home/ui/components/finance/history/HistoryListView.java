package com.smirnoff.home.ui.components.finance.history;

import com.smirnoff.home.ui.components.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Transaction history")
@Route(value = "finance/history", layout = MainLayout.class)
public class HistoryListView extends VerticalLayout {

    public HistoryListView() {

    }
}
